package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.ArrayBlock;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.property.Data;
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
        ObjectBlock data = new ObjectBlock();
        
        if (getPropertyLastChange(Data.CHANGE_ADDED_NAME) != null) {
            Set<C3ViewDataSet> load = (Set<C3ViewDataSet>) getPropertyChangeSet(Data.CHANGE_ADDED_NAME);
            data.addChild(new ValueBlock("columns", new ArrayBlock(JSTools.columns(load))));
            ObjectBlock types = new ObjectBlock();
            types.setName("types");
            for (C3ViewDataSet dataSet : load) {
                if (dataSet.getType() != null) {
                    types.addChild(new ValueBlock(dataSet.getId(), dataSet.getType().getName(), true));
                }
            }
            data.addChild(types);
            
        }
        if (getPropertyLastChange(Data.CHANGE_REMOVED_NAME) != null) {
            Set<C3ViewDataSet> unload = (Set<C3ViewDataSet>) getPropertyChangeSet(Data.CHANGE_REMOVED_NAME);
            Set<String> keys = new HashSet<>();
            for (C3ViewDataSet dataSet : unload) {
                keys.add(dataSet.getId());
            }
            data.addChild(new ValueBlock("unload", new ArrayBlock(JSTools.commaSeparatedStringsQuoted(keys))));
        }
        return data;
    }
}
