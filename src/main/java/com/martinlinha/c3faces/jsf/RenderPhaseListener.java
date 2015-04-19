package com.martinlinha.c3faces.jsf;

import com.martinlinha.c3faces.component.C3Chart;
import javax.faces.component.UIComponent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author Martin Linha
 */
public class RenderPhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("After phase listener: ");
        for (UIComponent comp : event.getFacesContext().getViewRoot().getChildren()) {
            resetModifiers(comp);
            resetChildren(comp);
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    private boolean resetChildren(UIComponent c) {
        for (UIComponent comp : c.getChildren()) {
            resetModifiers(comp);
            resetChildren(comp);
        }
        return false;
    }

    private void resetModifiers(UIComponent component) {
        if (component instanceof C3Chart) {
            C3Chart chart = (C3Chart) component;
            chart.resetState();
        }
    }
}
