package com.martinlinha.c3faces.component.property;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

/**
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.RegionProperty")

public class RegionProperty extends UIComponentBase {

    public static final String ATTR_AXIS = "axis";
    public static final String ATTR_START = "start";
    public static final String ATTR_END = "end";
    public static final String ATTR_CSS_CLASS = "cssClass";

    @Override
    public String getFamily() {
        return "com.martinlinha.c3faces.component.property";
    }
}
