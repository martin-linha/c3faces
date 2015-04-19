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
 *
 * @author Martin Linha
 */
public class C3PartialViewContext extends PartialViewContextWrapper {

    private PartialViewContext wrapped;
    private List<String> callbackScripts;
    private C3PartialResponseWriter writer;

    public C3PartialViewContext(PartialViewContext wrapped) {
        this.wrapped = wrapped;
    }

    public void addCallbackScripts(String script) {
        if (callbackScripts == null) {
            callbackScripts = new ArrayList<>();
        }
        callbackScripts.add(script);
    }

    public List<String> getCallbackScripts() {
        return callbackScripts;
    }

    public void setCallbackScripts(List<String> callbackScripts) {
        this.callbackScripts = callbackScripts;
    }

    @Override
    public PartialViewContext getWrapped() {
        return wrapped;
    }

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

    @Override
    public PartialResponseWriter getPartialResponseWriter() {

        if (writer == null) {
            writer = new C3PartialResponseWriter(this, super.getPartialResponseWriter());
        }

        return writer;
    }

    private static class C3PartialResponseWriter extends PartialResponseWriter {

        private final C3PartialViewContext ctx;

        public C3PartialResponseWriter(C3PartialViewContext ctx, ResponseWriter writer) {
            super(writer);
            this.ctx = ctx;
        }

        @Override
        public void endDocument() throws IOException {
            startEval();
            if (ctx.getCallbackScripts() != null) {
                for (String script : ctx.getCallbackScripts()) {
                    write(script + ";");
                }
            }
            endEval();
            ctx.setCallbackScripts(null);
            super.endDocument();
        }
    }
}
