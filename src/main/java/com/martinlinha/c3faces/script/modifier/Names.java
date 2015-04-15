package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.listener.change.Change;
import com.martinlinha.c3faces.listener.change.ViewDataSetChange;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;
import com.martinlinha.c3faces.script.property.Data;
import com.martinlinha.c3faces.script.Property;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Martin Linha
 */
public class Names extends Modifier {

    public Names() {
    }

    @Override
    protected Property getModificationProperty() {
        ObjectProp objProp = new ObjectProp();

        if (getPropertyLastChange(Data.CHANGE_ADDED_NAME) != null) {
            Set<C3ViewDataSet> load = (Set<C3ViewDataSet>) getPropertyChangeSet(Data.CHANGE_ADDED_NAME);
            for (C3ViewDataSet dataSet : load) {
                objProp.addChild(new ValueProp(dataSet.getId(), dataSet.getName(), true));
            }
        }

        for (ViewDataSetChange change : getViewDataSetChanges()) {
            for (Object ch : change.getChangeSet()) {
                Change propertyChange = (Change) ch;
                if (propertyChange.getName().equals("name")) {
                    objProp.addChild(new ValueProp(change.getName(), (String) propertyChange.getLastChange(), true));
                }
            }

        }
        return objProp;
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList("data");
    }

    @Override
    protected String getMethodName() {
        return "names";
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
