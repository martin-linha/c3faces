package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.Size;

/**
 *
 * @author Martin Linha
 */
public class Resize extends Modifier {
    
    private static final String RESIZE = "resize";

    @Override
    protected Property getModificationProperty() {
        return new Size((Integer) getPropertyLastChange(Size.EVENT_WIDTH_CHANGED), (Integer) getPropertyLastChange(Size.EVENT_HEIGHT_CHANGED));
    }

    @Override
    protected String getMethodName() {
        return RESIZE;
    }
}
