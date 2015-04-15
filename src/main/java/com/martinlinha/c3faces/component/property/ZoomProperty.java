package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.OnzoomMethod;
import com.martinlinha.c3faces.script.property.Zoom;
import javax.faces.component.FacesComponent;

/**
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.ZoomProperty")
public class ZoomProperty extends C3Property {

    private enum PropertyKeys {

        enabled, rescale, extentFrom, extentTo, onzoom
    }

    private Property associatedProperty;

    @Override
    public Property getAssociatedProperty() {
        if (associatedProperty == null) {
            OnzoomMethod prop = null;
            if (getOnzoom() != null) {
                prop = new OnzoomMethod(getOnzoom());
            }
            associatedProperty = new Zoom(getEnabled(), getRescale(), getExtentFrom(), getExtentTo(), prop);
        }
        return associatedProperty;
    }

    public Boolean getEnabled() {
        return (Boolean) getStateHelper().eval(PropertyKeys.enabled);
    }

    public void setEnabled(Boolean enabled) {
        getStateHelper().put(PropertyKeys.enabled, enabled);
    }

    public Boolean getRescale() {
        return (Boolean) getStateHelper().eval(PropertyKeys.rescale);
    }

    public void setRescale(Boolean rescale) {
        getStateHelper().put(PropertyKeys.rescale, rescale);
    }

    public Integer getExtentFrom() {
        return (Integer) getStateHelper().eval(PropertyKeys.extentFrom);
    }

    public void setExtentFrom(Integer extentFrom) {
        getStateHelper().put(PropertyKeys.extentFrom, extentFrom);
    }

    public Integer getExtentTo() {
        return (Integer) getStateHelper().eval(PropertyKeys.extentTo);
    }

    public void setExtentTo(Integer extentTo) {
        getStateHelper().put(PropertyKeys.extentTo, extentTo);
    }

    public String getOnzoom() {
        return (String) getStateHelper().eval(PropertyKeys.onzoom);
    }

    public void setOnzoom(String onzoom) {
        getStateHelper().put(PropertyKeys.onzoom, onzoom);
    }
}
