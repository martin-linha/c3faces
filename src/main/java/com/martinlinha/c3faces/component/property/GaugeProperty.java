package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.GaugeProperties;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 * This class allows to declare visual property of type com.martinlinha.c3faces.script.property.GaugeProperty in facelet.
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.GaugeProperty")
public class GaugeProperty extends C3Property {

    private static final String ATTR_WIDTH = "width";
    private static final String ATTR_MIN = "min";
    private static final String ATTR_MAX = "max";
    private static final String ATTR_UNITS = "units";
    private static final String ATTR_EXPAND = "expand";
    private static final String ATTR_SHOW_LABELS = "showLabels";

    @Override
    public Property getAssociatedProperty() {
        return new GaugeProperties(ComponentUtil.parseInteger(getAttributes().get(ATTR_WIDTH)), ComponentUtil.parseInteger(getAttributes().get(ATTR_MIN)),
                ComponentUtil.parseInteger(getAttributes().get(ATTR_MAX)), (String) getAttributes().get(ATTR_UNITS),
                ComponentUtil.parseBoolean(getAttributes().get(ATTR_EXPAND)), ComponentUtil.parseBoolean(getAttributes().get(ATTR_SHOW_LABELS)));
    }
}
