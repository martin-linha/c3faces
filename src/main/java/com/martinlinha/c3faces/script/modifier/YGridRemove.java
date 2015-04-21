package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.property.GridProperties;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class YGridRemove extends Modifier {

    private static final String REMOVE = "remove";
    private static final String YGRIDS = "ygrids";

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
        return Arrays.asList(YGRIDS);
    }

    @Override
    public boolean isMethod() {
        if (getPropertyLastChange(GridProperties.EVENT_YGRID_REMOVED) != null) {
            return (boolean) getPropertyLastChange(GridProperties.EVENT_YGRID_REMOVED);
        }
        return false;
    }
}
