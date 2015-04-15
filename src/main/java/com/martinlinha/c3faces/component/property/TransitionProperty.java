package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.Transition;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.TransitionProperty")
public class TransitionProperty extends C3Property {

    private static final String ATTR_DURATION = "duration";

    @Override
    public Property getAssociatedProperty() {
        return new Transition(ComponentUtil.parseInteger(getAttributes().get(ATTR_DURATION)));
    }
}
