package com.martinlinha.c3faces.jsf;

import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.context.PartialViewContextFactory;

/**
 * Factory which is used to return extended version of PartialViewContext to allow writing callback scripts in eval part of response.
 *
 * @author Martin Linha
 */
public class C3PartialViewContextFactory extends PartialViewContextFactory {

    private final PartialViewContextFactory wrapped;

    public C3PartialViewContextFactory(PartialViewContextFactory wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * Return C3PartialViewContext to provide required funcionality.
     *
     * @param context Actual FacesContext instance
     * @return C3PartialViewContext instance
     */
    @Override
    public PartialViewContext getPartialViewContext(FacesContext context) {
        return new C3PartialViewContext(wrapped.getPartialViewContext(context));
    }

    /**
     * Return wrapped factory
     *
     * @return Wrapped PartialViewContextFactory
     */
    @Override
    public PartialViewContextFactory getWrapped() {
        return wrapped;
    }
}
