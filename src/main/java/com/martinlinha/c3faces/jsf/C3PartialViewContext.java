package com.martinlinha.c3faces.jsf;

import com.martinlinha.c3faces.util.Faces;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialResponseWriter;
import javax.faces.context.PartialViewContext;
import javax.faces.context.PartialViewContextWrapper;
import javax.faces.context.ResponseWriter;

/**
 * This class is used to fix Mojarra problems with EVAL part of AJAX response. Components are not able to put script in eval part without problem of
 * malformed XML.
 *
 * Through methods of this class is possible to add callback scripts, which will be properly added at the end of AJAX response.
 *
 * @author Martin Linha
 */
public class C3PartialViewContext extends PartialViewContextWrapper {

    private final PartialViewContext wrapped;
    private List<String> callbackScripts;
    private C3PartialResponseWriter writer;

    public C3PartialViewContext(PartialViewContext wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * Adds scripts to be evaluated at the end of AJAX response in eval part.
     *
     * @param script To be evaluated
     */
    public void addCallbackScripts(String script) {
        if (callbackScripts == null) {
            callbackScripts = new ArrayList<>();
        }
        callbackScripts.add(script);
    }

    /**
     * Returns of call back scripts which will be evaluated at the end of actual AJAX response.
     *
     * @return Callback scripts
     */
    public List<String> getCallbackScripts() {
        return callbackScripts;
    }

    /**
     * Sets list of call back scripts which will be evaluated at the end of actual AJAX response.
     *
     * @param callbackScripts List of callback scripts to be evaluated
     */
    public void setCallbackScripts(List<String> callbackScripts) {
        this.callbackScripts = callbackScripts;
    }

    /**
     * Returns wrapped PartialViewContext instance.
     *
     * @return Wrapped PartialViewContext instance
     */
    @Override
    public PartialViewContext getWrapped() {
        return wrapped;
    }

    /**
     * Get current instance of this C3PartialViewContext. Typically used when is needed adding callback scripts.
     *
     * @param context Actual FacesContext instance
     * @return Current instance of C3PartialViewContext
     */
    public static C3PartialViewContext getCurrentInstance(FacesContext context) {
        C3PartialViewContext instance = Faces.getContextAttribute(context, C3PartialViewContext.class.getName());

        if (instance != null) {
            return instance;
        }

        instance = unwrap(context.getPartialViewContext());

        if (instance != null) {
            setCurrentInstance(context, instance);
            return instance;
        }
        return null;
    }

    /**
     * Tries to unwrap C3PartialViewContext instance.
     *
     * Used when C3PartialViewContext is not contained in context attributes of FacesContext.
     *
     * @param context Actual FacesContext instance
     * @param instance C3PartialViewContext instance
     */
    private static void setCurrentInstance(FacesContext context, C3PartialViewContext instance) {
        Faces.setContextAttribute(context, C3PartialViewContext.class.getName(), instance);
    }

    private static C3PartialViewContext unwrap(PartialViewContext context) {
        PartialViewContext unwrappedContext = context;

        while (!(unwrappedContext instanceof C3PartialViewContext) && unwrappedContext instanceof PartialViewContextWrapper) {
            unwrappedContext = ((PartialViewContextWrapper) unwrappedContext).getWrapped();
        }

        if (unwrappedContext instanceof C3PartialViewContext) {
            return (C3PartialViewContext) unwrappedContext;
        } else {
            return null;
        }
    }

    /**
     * Override to return new instance of C3PartialResponseWriter.
     *
     * @return C3PartialResponseWriter instance
     */
    @Override
    public PartialResponseWriter getPartialResponseWriter() {

        if (writer == null) {
            writer = new C3PartialResponseWriter(this, super.getPartialResponseWriter());
        }

        return writer;
    }

    /**
     * This class is responsible to add some behavior to PartialResponseWriter. At the end of document it will writes all of callback scripts
     * contained in PartialViewContext.
     */
    private static class C3PartialResponseWriter extends PartialResponseWriter {

        private final C3PartialViewContext ctx;

        public C3PartialResponseWriter(C3PartialViewContext ctx, ResponseWriter writer) {
            super(writer);
            this.ctx = ctx;
        }

        /**
         * If in PartialViewContext are any callback scripts, writes them in eval part and clean the list.
         *
         * @throws IOException
         */
        @Override
        public void endDocument() throws IOException {
            if (ctx.getCallbackScripts() != null) {
                startEval();
                for (String script : ctx.getCallbackScripts()) {
                    write(script);
                }
                endEval();
            }
            ctx.setCallbackScripts(null);
            super.endDocument();
        }
    }
}
