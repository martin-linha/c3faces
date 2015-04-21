package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.Point;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 * This class allows to declare visual property of type com.martinlinha.c3faces.script.property.Point in facelet.
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.PointProperty")
public class PointProperty extends C3Property {

    private static final String ATTR_SHOW = "show";
    private static final String ATTR_R = "r";
    private static final String ATTR_EXPAND = "expand";
    private static final String ATTR_EXPAND_R = "expandR";
    private static final String ATTR_SELECT_R = "selectR";

    @Override
    public Property getAssociatedProperty() {
        return new Point(ComponentUtil.parseBoolean(getAttributes().get(ATTR_SHOW)), ComponentUtil.parseDouble(getAttributes().get(ATTR_R)),
                ComponentUtil.parseBoolean(getAttributes().get(ATTR_EXPAND)), ComponentUtil.parseDouble(getAttributes().get(ATTR_EXPAND_R)),
                ComponentUtil.parseDouble(getAttributes().get(ATTR_SELECT_R)));
    }
}
