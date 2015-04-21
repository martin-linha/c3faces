package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.Pie;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 * This class allows to declare visual property of type com.martinlinha.c3faces.script.property.Pie in facelet.
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.PieProperty")
public class PieProperty extends C3Property {

    private static final String ATTR_EXPAND = "expand";
    private static final String ATTR_SHOW_LABELS = "showLabels";

    @Override
    public Property getAssociatedProperty() {
        return new Pie(ComponentUtil.parseBoolean(getAttributes().get(ATTR_EXPAND)), ComponentUtil.parseBoolean(getAttributes().get(ATTR_SHOW_LABELS)));
    }
}
