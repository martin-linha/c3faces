package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.Tooltip;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 * This class allows to declare visual property of type com.martinlinha.c3faces.script.property.Tooltip in facelet.
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.TooltipProperty")
public class TooltipProperty extends C3Property {

    private static final String ATTR_SHOW = "show";
    private static final String ATTR_GROUPED = "grouped";

    @Override
    public Property getAssociatedProperty() {
        return new Tooltip(ComponentUtil.parseBoolean(getAttributes().get(ATTR_SHOW)), ComponentUtil.parseBoolean(getAttributes().get(ATTR_GROUPED)));
    }
}
