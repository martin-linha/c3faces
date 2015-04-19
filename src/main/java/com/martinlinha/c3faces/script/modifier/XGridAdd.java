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
public class XGridAdd extends Modifier {

    @Override
    protected String getMethodName() {
        return "add";
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList("xgrids");
    }

    @Override
    protected Property getModificationProperty() {
        ArrayBlock xgridArray = new ArrayBlock();

        Set<Grid> grids = (Set<Grid>) getPropertyChangeSet("xGridAdd");
        if (grids != null) {

            for (Grid grid : grids) {
                ObjectBlock obj = new ObjectBlock();
                obj.addChild(new ValueBlock("value", grid.getValue()));
                obj.addChild(new ValueBlock("text", grid.getText(), true));
                xgridArray.addChild(obj);
            }
        }
        return xgridArray;
    }
}
