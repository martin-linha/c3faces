package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.Tooltip;
import javax.faces.component.FacesComponent;

/**
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.TooltipProperty")
public class TooltipProperty extends C3Property {

    private enum PropertyKeys {

        show, grouped
    }

    private Property associatedProperty;

    @Override
    public Property getAssociatedProperty() {
        if (associatedProperty == null) {
            associatedProperty = new Tooltip(getShow(), getGrouped());
        }
        return associatedProperty;
    }

    public Boolean getShow() {
        return (Boolean) getStateHelper().eval(PropertyKeys.show);
    }

    public void setShow(Boolean show) {
        getStateHelper().put(PropertyKeys.show, show);
    }

    public Boolean getGrouped() {
        return (Boolean) getStateHelper().eval(PropertyKeys.grouped);
    }

    public void setGrouped(Boolean grouped) {
        getStateHelper().put(PropertyKeys.grouped, grouped);
    }

}
