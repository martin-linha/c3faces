package com.martinlinha.c3faces.component.property;

import javax.faces.component.UIComponentBase;

/**
 *
 * @author Martin Linha
 */
public class GridProperty extends UIComponentBase {

    public static final String ATTR_VALUE = "value";
    public static final String ATTR_TEXT = "text";

    @Override
    public String getFamily() {
        return "com.martinlinha.c3faces.component.property";
    }

}
