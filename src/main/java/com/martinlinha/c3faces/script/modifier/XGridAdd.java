package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.ArrayBlock;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.property.Grid;
import com.martinlinha.c3faces.script.property.GridProperties;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Martin Linha
 */
public class XGridAdd extends Modifier {

    private static final String ADD = "add";
    private static final String XGRIDS = "xgrids";

    @Override
    protected String getMethodName() {
        return ADD;
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList(XGRIDS);
    }

    @Override
    protected Property getModificationProperty() {
        ArrayBlock xgridArray = new ArrayBlock();

        Set<Grid> grids = (Set<Grid>) getPropertyChangeSet(GridProperties.EVENT_XGRID_ADDED);
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
