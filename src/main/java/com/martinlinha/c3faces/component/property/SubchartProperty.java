package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.property.OnbrushMethod;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.Subchart;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.SubchartProperty")
public class SubchartProperty extends C3Property {

    private static final String ATTR_SHOW = "show";
    private static final String ATTR_HEIGHT = "height";
    private static final String ATTR_ONBRUSHMETHOD = "onbrush";

    @Override
    public Property getAssociatedProperty() {
        Subchart subchart = new Subchart(ComponentUtil.parseBoolean(getAttributes().get(ATTR_SHOW)),
                ComponentUtil.parseInteger(getAttributes().get(ATTR_HEIGHT)), null);
        String onbrush = (String) getAttributes().get(ATTR_ONBRUSHMETHOD);
        if (onbrush != null) {
            subchart.setOnbrushMethodProp(new OnbrushMethod(onbrush));
        }
        return subchart;
    }

}
