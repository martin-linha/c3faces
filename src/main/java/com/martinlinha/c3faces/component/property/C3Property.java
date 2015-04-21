package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import javax.faces.component.UIComponentBase;

/**
 * Class for transforming objects of type com.martinlinha.c3faces.component.property.Property to UIComponents. After this is made, component can be
 * declared in facelet in body of chart's tag. Chart will then easy looks for objects of this type, takes the associated
 * com.martinlinha.c3faces.component.property.Property object and add it to its property collection.
 *
 * @author Martin Linha
 */
public abstract class C3Property extends UIComponentBase {

    /**
     * Component won't even render, so renderer is unnecessary. Return null.
     *
     * @return null
     */
    @Override
    public String getRendererType() {
        return null;
    }

    @Override
    public String getFamily() {
        return "com.martinlinha.c3faces.component.property";
    }

    /**
     * Through this method is possible to supply the visual property of type com.martinlinha.c3faces.component.property.Property. After this
     * UIComponent is declared in body of facelet, chart will takes the returned object and adds it to chart's properties.
     *
     * @return Property which should be added to chart's properties
     */
    public abstract Property getAssociatedProperty();
}
