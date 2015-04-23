package com.martinlinha.c3faces.model;

import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.listener.ChangeListener;
import com.martinlinha.c3faces.listener.change.Change;
import com.martinlinha.c3faces.listener.change.ViewDataSetCumulatibleChange;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Class represents view specific attributes of data sets (series). It contains color, name and chart type rendered on client. Changes made to this
 * attibutes are catched and all registered listeners are notified.
 *
 * By C3.js spec it's neccessary to provide unique id. This is in competence of this class - creates and set up 16 chars long id in constructors.
 *
 * @author Martin Linha
 */
public class C3ViewDataSet implements Serializable {

    private static final int RANDOM_ID_LENGTH = 16;
    public static String EVENT_DATA_CHART_TYPE = "viewDataSetType";
    public static String EVENT_DATA_CHART_NAME = "viewDataSetName";
    public static String EVENT_DATA_CHART_COLOR = "viewDataSetColor";
    public static String EVENT_NEW_DATA_SET = "viewDataSetAddedSet";

    private final String id;

    private List<ChangeListener> listeners = new ArrayList<>();
    private String color;
    private String name;
    private String type;
    private C3DataSet dataSet;

    /**
     * Constructor which also creates and sets unique id.
     *
     */
    public C3ViewDataSet() {
        this.id = RandomStringUtils.randomAlphabetic(RANDOM_ID_LENGTH);
    }

    /**
     * Constructor which also creates and sets unique id.
     *
     * @param name Name to show on chart
     */
    public C3ViewDataSet(String name) {
        this.name = name;
        this.id = RandomStringUtils.randomAlphabetic(RANDOM_ID_LENGTH);
    }

    /**
     * Constructor which also creates and sets unique id.
     *
     * @param name Name to show on chart
     * @param dataSet Data series to show on chart
     * @param color Color of data series
     */
    public C3ViewDataSet(String name, C3DataSet dataSet, String color) {
        this.name = name;
        this.dataSet = dataSet;
        this.color = color;
        this.id = RandomStringUtils.randomAlphabetic(RANDOM_ID_LENGTH);
    }

    /**
     * Returns unique ID of this instance.
     *
     * @return Unique ID of this instance
     */
    public String getId() {
        return id;
    }

    /**
     * Returns name for data series which will be shown on chart.
     *
     * @return Name for data series which will be shown on chart
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name for data series which will be shown on chart. Fires corresponding event.
     *
     * @param name Name to be shown on chart
     */
    public void setName(String name) {
        fire(id, new Change(EVENT_DATA_CHART_NAME, name));
        this.name = name;
    }

    /**
     * Returns color for data series which will be shown on chart.
     *
     * @return Color for data series which will be shown on chart
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets color for data series which will be shown on chart. Fires corresponding event.
     *
     * @param color Color to be shown on chart
     */
    public void setColor(String color) {
        fire(id, new Change(EVENT_DATA_CHART_COLOR, color));
        this.color = color;
    }

    /**
     * Returns visual chart type of data series which will be shown on chart. Corresponding to C3.js specification, default (if type is null) is LINE.
     *
     * @return Visual chart type of data series which will be shown on chart
     */
    public String getType() {
        if (type == null) {
            return ChartType.LINE.getName();
        }
        return type;
    }

    /**
     * Returns all registered listeners.
     *
     * @return All registered listeners
     */
    public List<ChangeListener> getListeners() {
        return listeners;
    }

    /**
     * Register new listener.
     *
     * @param listener New listener to register
     */
    public void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Set visual chart type of data set which will be rendered on chart. Fires corresponding event.
     *
     * @param type Visual chart type of data set
     */
    public void setType(String type) {
        fire(id, new Change(EVENT_DATA_CHART_TYPE, type));
        this.type = type;
    }

    /**
     * Returns data set with values rendered on chart.
     *
     * @return Data set with values
     */
    public C3DataSet getDataSet() {
        return dataSet;
    }

    /**
     * Set new data set with values to be rendered on chart. Fires corresponding event.
     *
     * @param dataSet New data set
     */
    public void setDataSet(C3DataSet dataSet) {
        fire(id, new Change(EVENT_NEW_DATA_SET, this));
        this.dataSet = dataSet;
    }

    /**
     * Method which notify all listeners with changes made to this class. Change is cumulatible, ie. it saves all changes, not only last change. Fires
     * corresponding event.
     *
     * @param name Name of change
     * @param value Value of change
     */
    private void fire(String name, Object value) {
        for (ChangeListener listener : listeners) {
            listener.onChange(new ViewDataSetCumulatibleChange(name, value));
        }
    }

    /**
     * Set collection of registered listeners.
     *
     * @param listeners Collection of registered listeners to be set
     */
    public void setListeners(List<ChangeListener> listeners) {
        this.listeners = listeners;
    }
}
