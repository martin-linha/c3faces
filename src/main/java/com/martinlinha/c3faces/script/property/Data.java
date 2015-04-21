package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.listener.C3ViewDataObservableSet;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.ArrayBlock;
import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.util.JSTools;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Martin Linha
 */
public class Data extends ObjectBlock {

    public static String NAME = "data";

    public static final String EVENT_VIEW_DATA_SET_ADDED = "viewDataSetAdded";
    public static final String EVENT_VIEW_DATA_SET_REMOVED = "viewDataSetRemoved";
    private ChartType chartType;
    private boolean selection = true;
    private final C3ViewDataObservableSet dataSetsObserver
            = new C3ViewDataObservableSet(new HashSet(), EVENT_VIEW_DATA_SET_ADDED, EVENT_VIEW_DATA_SET_REMOVED);

    public Data() {
        dataSetsObserver.setListeners(getListeners());
    }

    @Override
    protected void preScriptBuild() {
        if (dataSetsObserver != null && !dataSetsObserver.isEmpty()) {
            addChild(new ValueBlock("columns", new ArrayBlock(JSTools.columns(dataSetsObserver))));
        } else {
            addChild(new ValueBlock("columns", new ArrayBlock(" ")));
        }

        if (dataSetsObserver != null && !dataSetsObserver.isEmpty()) {
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
                    typesObj.addChild(new ValueBlock(data.getId(), data.getType().getName(), true));
                }
            }
            addChild(namesObj);
            addChild(colorsObj);
            addChild(typesObj);
            if (chartType != null) {
                addChild(new ValueBlock("type", chartType.getName(), true));
            }
            if (isSelection()) {
                addChild(new ObjectBlock("selection", new ValueBlock("enabled", selection), new ValueBlock("multiple", false)));
            }
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public ChartType getChartType() {
        return chartType;
    }

    public void setChartType(ChartType chartType) {
        fire("chartType", chartType);
        this.chartType = chartType;
    }

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

    public void setDataSets(Set<C3ViewDataSet> dataSets) {
        this.dataSetsObserver.setWrappedSet(dataSets);
    }

    public boolean isSelection() {
        return selection;
    }

    public void setSelection(boolean selection) {
        this.selection = selection;
    }
}
