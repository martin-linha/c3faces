package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.listener.C3ViewDataObservableSet;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.ArrayBlock;
import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.util.JSTools;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Holder of all data sets rendered in chart. Using com.martinlinha.c3faces.listener.C3ViewDataObservableSet to notify listeners with corresponding
 * events.
 *
 * NOTE: Each C3Chart component is forced to have instance of this class because of C3.js specifiation (all chart have to have data section, otherwise
 * throws .js error)
 *
 * @see C3ViewDataObservableSet
 * @author Martin Linha
 */
public class Data extends ObjectBlock {

    public static String NAME = "data";

    public static final String EVENT_VIEW_DATA_SET_ADDED = "viewDataSetAdded";
    public static final String EVENT_VIEW_DATA_SET_REMOVED = "viewDataSetRemoved";
    public static final String EVENT_CHART_TYPE_CHANGED = "dataChartTypeChanged";

    private String chartType;
    private boolean selection = true;
    private final C3ViewDataObservableSet dataSetsObserver
            = new C3ViewDataObservableSet(new LinkedHashSet<C3ViewDataSet>(), EVENT_VIEW_DATA_SET_ADDED, EVENT_VIEW_DATA_SET_REMOVED);

    public Data() {
        dataSetsObserver.setListeners(getListeners());
    }

    @Override
    protected void preScriptBuild() {
        if (!dataSetsObserver.isEmpty()) {
            addChild(new ValueBlock("columns", new ArrayBlock(JSTools.columns(dataSetsObserver))));
        } else {
            addChild(new ValueBlock("columns", new ArrayBlock(" ")));
        }

        ObjectBlock namesObj = new ObjectBlock();
        ObjectBlock colorsObj = new ObjectBlock();
        ObjectBlock typesObj = new ObjectBlock();

        namesObj.setName("names");
        colorsObj.setName("colors");
        typesObj.setName("types");

        for (C3ViewDataSet data : dataSetsObserver) {
            namesObj.addChild(new ValueBlock(data.getId(), data.getName(), true));
            colorsObj.addChild(new ValueBlock(data.getId(), data.getColor(), true));
            if (data.getType() != null) {
                typesObj.addChild(new ValueBlock(data.getId(), data.getType(), true));
            }
        }
        addChild(namesObj);
        addChild(colorsObj);
        addChild(typesObj);
        addChild(new ValueBlock("type", chartType, true));
        if (isSelection()) {
            addChild(new ObjectBlock("selection", new ValueBlock("enabled", selection), new ValueBlock("multiple", false)));
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public String getChartType() {
        return chartType;
    }

    /**
     * Sets new chart type. Change will be properly listened and listeners properly notified.
     *
     * @param chartType New chart type
     */
    public void setChartType(String chartType) {
        fire(EVENT_CHART_TYPE_CHANGED, chartType);
        this.chartType = chartType;
    }

    /**
     * Returns C3ViewDataSet by its unique id
     *
     * @param id Unique id
     * @return C3ViewDataSet with specified unique id
     */
    public C3ViewDataSet getDataSetById(String id) {
        for (C3ViewDataSet sets : dataSetsObserver) {
            if (sets.getId().equals(id)) {
                return sets;
            }
        }
        return null;
    }

    public Set<C3ViewDataSet> getDataSets() {
        return dataSetsObserver;
    }

    /**
     * Sets LinkedHashSet instance as a wrapped collection in C3ViewDataObservableSet object. Change will be properly listened and listeners properly
     * notified.
     *
     * @param dataSets
     */
    public void setDataSets(LinkedHashSet<C3ViewDataSet> dataSets) {
        this.dataSetsObserver.setWrappedSet(dataSets);
    }

    public boolean isSelection() {
        return selection;
    }

    public void setSelection(boolean selection) {
        this.selection = selection;
    }
}
