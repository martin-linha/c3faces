package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.listener.change.Change;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 * Instances of this class can be registered as a listeners to listen changes fired by corresponding objects.
 *
 * The main mission of this class is to provide C3.js scripts for dynamic chart type changes.
 *
 * @author Martin Linha
 */
public class TransformTypes extends Modifier {

    @Override
    protected Property getModificationProperty() {
        getModifiers().clear();

        for (Change change : getViewDataSetChanges()) {
            for (Object ch : change.getChangeSet()) {
                Change propertyChange = (Change) ch;
                if (propertyChange.getName().equals(C3ViewDataSet.EVENT_DATA_CHART_TYPE)) {
                    addModifier(new TransformType(change.getName(), (String) propertyChange.getLastChange()));
                }
            }
        }
        return new ValueBlock();
    }

    @Override
    protected String getMethodName() {
        return "";
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
