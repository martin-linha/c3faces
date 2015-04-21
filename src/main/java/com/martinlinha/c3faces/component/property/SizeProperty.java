package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.modifier.Resize;
import com.martinlinha.c3faces.script.property.Size;
import com.martinlinha.c3faces.util.ComponentUtil;
import javax.faces.component.FacesComponent;

/**
 * This class allows to declare visual property of type com.martinlinha.c3faces.script.property.Size in facelet.
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.SizeProperty")
public class SizeProperty extends C3Property {

    private static final String ATTR_WIDTH = "width";
    private static final String ATTR_HEIGTH = "heigth";

    @Override
    public Property getAssociatedProperty() {
        Size size = new Size(ComponentUtil.parseInteger(getAttributes().get(ATTR_WIDTH)), ComponentUtil.parseInteger(getAttributes().get(ATTR_HEIGTH)));
        size.addListener(new Resize());
        return size;
    }
}
