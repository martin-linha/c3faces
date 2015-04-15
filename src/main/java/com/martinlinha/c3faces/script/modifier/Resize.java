package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.Size;

/**
 *
 * @author Martin Linha
 */
public class Resize extends Modifier {

    @Override
    protected Property getModificationProperty() {
        return new Size((Integer) getPropertyLastChange("width"), (Integer) getPropertyLastChange("height"));
    }

    @Override
    protected String getMethodName() {
        return "resize";
    }
}
