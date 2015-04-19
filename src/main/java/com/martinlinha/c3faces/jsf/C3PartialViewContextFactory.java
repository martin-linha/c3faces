package com.martinlinha.c3faces.jsf;

import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.context.PartialViewContextFactory;

/**
 *
 * @author Martin Linha
 */
public class C3PartialViewContextFactory extends PartialViewContextFactory {

    private final PartialViewContextFactory wrapped;

    public C3PartialViewContextFactory(PartialViewContextFactory wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public PartialViewContext getPartialViewContext(FacesContext context) {
        return new C3PartialViewContext(wrapped.getPartialViewContext(context));
    }

    @Override
    public PartialViewContextFactory getWrapped() {
        return wrapped;
    }

}
