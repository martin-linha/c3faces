package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.property.Area;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.AreaProperty")
public class AreaProperty extends C3Property {

    private static final String ATTR_ZEROBASED = "zerobased";

    @Override
    public Property getAssociatedProperty() {
        return new Area(ComponentUtil.parseBoolean(getAttributes().get(ATTR_ZEROBASED)));
    }
}
