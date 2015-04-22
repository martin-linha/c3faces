package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.listener.change.Change;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.property.Data;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Instances of this class can be registered as a listeners to listen changes fired by corresponding objects.
 *
 * The main mission of this class is to provide C3.js scripts for dynamic names chart changes.
 *
 * @author Martin Linha
 */
public class Names extends Modifier {

    private static final String NAMES = "names";
    private static final  String DATA = "data";

    public Names() {
    }

    @Override
    protected Property getModificationProperty() {
        ObjectBlock objProp = new ObjectBlock();

        if (getPropertyLastChange(Data.EVENT_VIEW_DATA_SET_ADDED) != null) {
            LinkedHashSet<C3ViewDataSet> load = (LinkedHashSet<C3ViewDataSet>) getPropertyChangeSet(Data.EVENT_VIEW_DATA_SET_ADDED);
            for (C3ViewDataSet dataSet : load) {
                objProp.addChild(new ValueBlock(dataSet.getId(), dataSet.getName(), true));
            }
        }

        for (Change change : getViewDataSetChanges()) {
            for (Object ch : change.getChangeSet()) {
                Change propertyChange = (Change) ch;
                if (propertyChange.getName().equals(C3ViewDataSet.EVENT_CHART_NAME)) {
                    objProp.addChild(new ValueBlock(change.getName(), (String) propertyChange.getLastChange(), true));
                }
            }
        }
        return objProp;
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList(DATA);
    }

    @Override
    protected String getMethodName() {
        return NAMES;
    }

    @Override
    public boolean isTimeoutable() {
        return false;
    }

    @Override
    public int getDuration() {
        return 0;
    }
}
