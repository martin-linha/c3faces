package com.martinlinha.c3faces.util;

import com.martinlinha.c3faces.jsf.C3PartialViewContext;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author Martin Linha
 */
public class Faces {

    @SuppressWarnings("unchecked")
    public static <T> T getContextAttribute(FacesContext context, String name) {
        return (T) context.getAttributes().get(name);
    }

    public static void setContextAttribute(FacesContext context, String name, Object value) {
        context.getAttributes().put(name, value);
    }

    public static void addCallbackScript(FacesContext ctx, String script) {
        C3PartialViewContext.getCurrentInstance(ctx).addCallbackScripts(script);
    }

    public static C3PartialViewContext getPartialViewContext(FacesContext ctx) {
        return C3PartialViewContext.getCurrentInstance(ctx);
    }

    public static Map<String, Object> getSessionMap(FacesContext ctx) {
        return ctx.getExternalContext().getSessionMap();
    }

    public static Map<String, String> getRequestParameterMap(FacesContext ctx) {
        return ctx.getExternalContext().getRequestParameterMap();
    }
}
