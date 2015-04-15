package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.ArrayProp;
import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;
import com.martinlinha.c3faces.listener.C3ViewDataObservableSet;
import com.martinlinha.c3faces.util.JSTools;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Martin Linha
 */
public class Data extends ObjectProp {

    public static String NAME = "data";

    public static final String CHANGE_ADDED_NAME = "viewDataSetAdded";
    public static final String CHANGE_REMOVED_NAME = "viewDataSetRemoved";
    private ChartType chartType;
    private boolean selection = true;
    private final C3ViewDataObservableSet dataSetsObserver
            = new C3ViewDataObservableSet(new HashSet(), CHANGE_ADDED_NAME, CHANGE_REMOVED_NAME);

    public Data() {
        dataSetsObserver.setListeners(getListeners());
    }

    @Override
    protected void preScriptBuild() {
        if (dataSetsObserver != null && !dataSetsObserver.isEmpty()) {
            addChild(new ValueProp("columns", new ArrayProp(JSTools.columns(dataSetsObserver))));
        } else {
            addChild(new ValueProp("columns", new ArrayProp(" ")));
        }

        if (dataSetsObserver != null && !dataSetsObserver.isEmpty()) {
            ObjectProp namesObj = new ObjectProp();
            ObjectProp colorsObj = new ObjectProp();
            namesObj.setName("names");
            colorsObj.setName("colors");
            for (C3ViewDataSet data : dataSetsObserver) {
                namesObj.addChild(new ValueProp(data.getId(), data.getName(), true));
                colorsObj.addChild(new ValueProp(data.getId(), data.getColor(), true));
            }
            addChild(namesObj);
            addChild(colorsObj);
            if (chartType != null) {
                addChild(new ValueProp("type", chartType.getName(), true));
            }
            if (isSelection()) {
                addChild(new ObjectProp("selection", new ValueProp("enabled", selection), new ValueProp("multiple", false)));
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
