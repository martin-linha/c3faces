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
 *
 * @author Martin Linha
 */
public class C3ViewDataSet implements Serializable {

    private static final int RANDOM_ID_LENGTH = 16;
    public static String EVENT_CHART_TYPE = "viewDataSetType";
    public static String EVENT_CHART_NAME = "viewDataSetName";
    public static String EVENT_CHART_COLOR = "viewDataSetColor";
    public static String EVENT_NEW_DATA_SET = "viewDataSetAddedSet";

    private final String id;

    private List<ChangeListener> listeners = new ArrayList<>();
    private String color;
    private String name;
    private ChartType type;
    private C3DataSet dataSet;

    public C3ViewDataSet() {
        this.id = RandomStringUtils.randomAlphabetic(RANDOM_ID_LENGTH);
    }

    public C3ViewDataSet(String name) {
        this.name = name;
        this.id = RandomStringUtils.randomAlphabetic(RANDOM_ID_LENGTH);
    }

    public C3ViewDataSet(String name, C3DataSet dataSet, String color) {
        this.name = name;
        this.dataSet = dataSet;
        this.color = color;
        this.id = RandomStringUtils.randomAlphabetic(RANDOM_ID_LENGTH);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        fire(id, new Change(EVENT_CHART_NAME, name));
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        fire(id, new Change(EVENT_CHART_COLOR, color));
        this.color = color;
    }

    public ChartType getType() {
        return type;
    }

    public List<ChangeListener> getListeners() {
        return listeners;
    }

    public void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void setType(ChartType type) {
        fire(id, new Change(EVENT_CHART_TYPE, type));
        this.type = type;
    }

    public C3DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(C3DataSet dataSet) {
        fire(id, new Change(EVENT_NEW_DATA_SET, this));
        this.dataSet = dataSet;
    }

    private void fire(String name, Object value) {
        for (ChangeListener listener : listeners) {
            listener.onChange(new ViewDataSetCumulatibleChange(name, value));
        }
    }

    public void setListeners(List<ChangeListener> listeners) {
        this.listeners = listeners;
    }
}
