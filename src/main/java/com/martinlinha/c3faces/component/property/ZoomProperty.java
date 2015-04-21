package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.OnzoomMethod;
import com.martinlinha.c3faces.script.property.Zoom;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 * This class allows to declare visual property of type com.martinlinha.c3faces.script.property.Zoom in facelet.
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.ZoomProperty")
public class ZoomProperty extends C3Property {

    private enum PropertyKeys {

        enabled, rescale, extentFrom, extentTo, onzoom
    }
    private static final String ATTR_ENABLED = "enabled";
    private static final String ATTR_RESCALE = "rescale";
    private static final String ATTR_EXTENT_FROM = "extentFrom";
    private static final String ATTR_EXTENT_TO = "extentTo";
    private static final String ATTR_ONZOOM = "onzoom";

    @Override
    public Property getAssociatedProperty() {
        String onzoomAttr = (String) getAttributes().get(ATTR_ONZOOM);
        OnzoomMethod onzoom = null;
        if (onzoomAttr != null) {
            onzoom = new OnzoomMethod(onzoomAttr);
        }

        return new Zoom(ComponentUtil.parseBoolean(ATTR_ENABLED),
                ComponentUtil.parseBoolean(ATTR_RESCALE),
                ComponentUtil.parseInteger(ATTR_EXTENT_FROM),
                ComponentUtil.parseInteger(ATTR_EXTENT_TO), onzoom);
    }
}
