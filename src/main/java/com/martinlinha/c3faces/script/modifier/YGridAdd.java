package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.ArrayProp;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueProp;
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
        ArrayProp ygridArray = new ArrayProp();

        Set<Grid> grids = (Set<Grid>) getPropertyChangeSet("yGridAdd");
        if (grids != null) {

            for (Grid grid : grids) {
                ObjectProp obj = new ObjectProp();
                obj.addChild(new ValueProp("value", grid.getValue()));
                obj.addChild(new ValueProp("text", grid.getText(), true));
                ygridArray.addChild(obj);
            }
        }
        return ygridArray;
    }
}
