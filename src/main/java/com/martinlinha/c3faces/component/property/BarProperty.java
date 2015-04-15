package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.property.BarProperties;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.BarProperty")
public class BarProperty extends C3Property {

    private static final String ZEROBASED = "zerobased";
    private static final String WIDTH = "width";

    @Override
    public Property getAssociatedProperty() {
        return new BarProperties(ComponentUtil.parseInteger(getAttributes().get(WIDTH)), Boolean.parseBoolean((String) getAttributes().get(ZEROBASED)));
    }
}
