package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.ArrayProp;
import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;
import com.martinlinha.c3faces.script.property.Data;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.util.JSTools;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Martin Linha
 */
public class Load extends Modifier {

    @Override
    protected String getMethodName() {
        return "load";
    }

    @Override
    protected Property getModificationProperty() {
        ObjectProp data = new ObjectProp();

        if (getPropertyLastChange(Data.CHANGE_ADDED_NAME) != null) {
            Set<C3ViewDataSet> load = (Set<C3ViewDataSet>) getPropertyChangeSet(Data.CHANGE_ADDED_NAME);
            data.addChild(new ValueProp("columns", new ArrayProp(JSTools.columns(load))));
        }
        if (getPropertyLastChange(Data.CHANGE_REMOVED_NAME) != null) {
            Set<C3ViewDataSet> unload = (Set<C3ViewDataSet>) getPropertyChangeSet(Data.CHANGE_REMOVED_NAME);
            Set<String> keys = new HashSet<>();
            for (C3ViewDataSet dataSet : unload) {
                keys.add(dataSet.getId());
            }
            data.addChild(new ValueProp("unload", new ArrayProp(JSTools.commaSeparatedStringsQuoted(keys))));
        }
        return data;
    }
}
