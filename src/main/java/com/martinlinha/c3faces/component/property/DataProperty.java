package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import javax.faces.component.FacesComponent;

/**
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.DataProperty")
public class DataProperty extends C3Property {

    private static final String ATTR_X_FORMAT = "xFormat";
    private static final String ATTR_CHART_TYPE = "chartType";
    private static final String ATTR_LABELS = "labels";
    private static final String ATTR_ORDER = "order";
    private static final String ATTR_HIDE = "hide";

    @Override
    public Property getAssociatedProperty() {
        return null;
    }
}
