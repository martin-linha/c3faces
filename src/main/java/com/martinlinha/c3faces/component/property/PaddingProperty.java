package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.property.Padding;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.PaddingProperty")
public class PaddingProperty extends C3Property {

    private static final String ATTR_TOP = "top";
    private static final String ATTR_BOTTOM = "bottom";
    private static final String ATTR_RIGHT = "right";
    private static final String ATTR_LEFT = "left";

    @Override
    public Property getAssociatedProperty() {
        return new Padding(ComponentUtil.parseInteger((String) getAttributes().get(ATTR_TOP)),
                ComponentUtil.parseInteger((String) getAttributes().get(ATTR_RIGHT)),
                ComponentUtil.parseInteger((String) getAttributes().get(ATTR_LEFT)),
                ComponentUtil.parseInteger((String) getAttributes().get(ATTR_BOTTOM)));
    }
}
