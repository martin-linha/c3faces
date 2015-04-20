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
 *
 * @author Martin Linha
 */
public class Names extends Modifier {

    public Names() {
    }

    @Override
    protected Property getModificationProperty() {
        ObjectBlock objProp = new ObjectBlock();

        if (getPropertyLastChange(Data.CHANGE_ADDED_NAME) != null) {
            Set<C3ViewDataSet> load = (Set<C3ViewDataSet>) getPropertyChangeSet(Data.CHANGE_ADDED_NAME);
            for (C3ViewDataSet dataSet : load) {
                objProp.addChild(new ValueBlock(dataSet.getId(), dataSet.getName(), true));
            }
        }

        for (Change change : getViewDataSetChanges()) {
            for (Object ch : change.getChangeSet()) {
                Change propertyChange = (Change) ch;
                if (propertyChange.getName().equals("name")) {
                    objProp.addChild(new ValueBlock(change.getName(), (String) propertyChange.getLastChange(), true));
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
