package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.ArrayBlock;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.property.Grid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Martin Linha
 */
public class YGridAdd extends Modifier {

    @Override
    protected String getMethodName() {
        return "add";
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList("ygrids");
    }

    @Override
    protected Property getModificationProperty() {
        ArrayBlock ygridArray = new ArrayBlock();

        Set<Grid> grids = (Set<Grid>) getPropertyChangeSet("yGridAdd");
        if (grids != null) {

            for (Grid grid : grids) {
                ObjectBlock obj = new ObjectBlock();
                obj.addChild(new ValueBlock("value", grid.getValue()));
                obj.addChild(new ValueBlock("text", grid.getText(), true));
                ygridArray.addChild(obj);
            }
        }
        return ygridArray;
    }
}
