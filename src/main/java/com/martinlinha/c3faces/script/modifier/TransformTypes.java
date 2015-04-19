package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.listener.change.Change;
import com.martinlinha.c3faces.listener.change.ViewDataSetChange;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.Property;

/**
 *
 * @author Martin Linha
 */
public class TransformTypes extends Modifier {

    @Override
    protected Property getModificationProperty() {
        getModifiers().clear();
        for (ViewDataSetChange change : getViewDataSetChanges()) {
            for (Object ch : change.getChangeSet()) {
                Change propertyChange = (Change) ch;
                if (propertyChange.getName().equals("type")) {
                    addModifier(new TransformType(change.getName(), (ChartType) propertyChange.getLastChange()));
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
