package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.DonutProperties;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 * This class allows to declare visual property of type com.martinlinha.c3faces.script.property.DonutProperty in facelet.
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.DonutProperty")
public class DonutProperty extends C3Property {

    private static final String ATTR_WIDTH = "width";
    private static final String ATTR_TITLE = "title";
    private static final String ATTR_EXPAND = "expand";
    private static final String ATTR_SHOWLABELS = "showLabels";

    @Override
    public Property getAssociatedProperty() {
        return new DonutProperties(ComponentUtil.parseInteger(getAttributes().get(ATTR_WIDTH)), (String) getAttributes().get(ATTR_TITLE),
                ComponentUtil.parseBoolean(getAttributes().get(ATTR_EXPAND)), ComponentUtil.parseBoolean(getAttributes().get(ATTR_SHOWLABELS)));
    }
}
