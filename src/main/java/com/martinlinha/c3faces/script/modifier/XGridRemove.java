package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.property.GridProperties;
import java.util.Arrays;
import java.util.List;

/**
 * Instances of this class can be registered as a listeners to listen changes fired by corresponding objects.
 *
 * The main mission of this class is to provide C3.js scripts for dynamic chart grid changes.
 *
 * @author Martin Linha
 */
public class XGridRemove extends Modifier {

    private static final String REMOVE = "remove";
    private static final String XGRIDS = "xgrids";

    @Override
    protected Property getModificationProperty() {
        return new ValueBlock();
    }

    @Override
    protected String getMethodName() {
        return REMOVE;
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList(XGRIDS);
    }

    @Override
    public boolean isMethod() {
        if (getPropertyLastChange(GridProperties.EVENT_XGRID_REMOVED) != null) {
            return (boolean) getPropertyLastChange(GridProperties.EVENT_XGRID_REMOVED);
        }
        return false;
    }
}
