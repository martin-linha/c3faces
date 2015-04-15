package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import javax.faces.component.UIComponentBase;

/**
 *
 * @author Martin Linha
 */
public abstract class C3Property extends UIComponentBase {

    @Override
    public String getFamily() {
        return "com.martinlinha.c3faces.component.property";
    }

    public abstract Property getAssociatedProperty();
}
