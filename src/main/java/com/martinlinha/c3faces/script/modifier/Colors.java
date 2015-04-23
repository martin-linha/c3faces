package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.listener.change.Change;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.property.Data;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Instances of this class can be registered as a listeners to listen changes fired by corresponding objects.
 *
 * The main mission of this class is to provide C3.js scripts for dynamic color chart changes.
 *
 * @author Martin Linha
 */
public class Colors extends Modifier {

    private static final String COLORS = "colors";
    private static final String DATA = "data";

    @Override
    protected Property getModificationProperty() {
        ObjectBlock objProp = new ObjectBlock();

        if (getPropertyLastChange(Data.EVENT_VIEW_DATA_SET_ADDED) != null) {
            Set<C3ViewDataSet> load = (Set<C3ViewDataSet>) getPropertyChangeSet(Data.EVENT_VIEW_DATA_SET_ADDED);
            for (C3ViewDataSet dataSet : load) {
                objProp.addChild(new ValueBlock(dataSet.getId(), dataSet.getColor(), true));
            }
        }

        for (Change change : getViewDataSetChanges()) {
            for (Object ch : change.getChangeSet()) {
                Change propertyChange = (Change) ch;
                if (propertyChange.getName().equals(C3ViewDataSet.EVENT_DATA_CHART_COLOR)) {
                    objProp.addChild(new ValueBlock(change.getName(), (String) propertyChange.getLastChange(), true));
                }
            }

        }

        return objProp;
    }

    @Override
    protected String getMethodName() {
        return COLORS;
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList(DATA);
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public boolean isTimeoutable() {
        return false;
    }
}
