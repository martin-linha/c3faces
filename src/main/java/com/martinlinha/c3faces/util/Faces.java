package com.martinlinha.c3faces.util;

import com.martinlinha.c3faces.jsf.C3PartialViewContext;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 * Class contains useful static JSF specific methods.
 *
 * @author Martin Linha
 */
public class Faces {

    /**
     * Rerturns attribute by specified name from specified FacesContext.
     *
     * @param <T>
     * @param context FacesContext in which will be searched
     * @param name Name of context parameter
     * @return Found object of specific type or null
     */
    @SuppressWarnings("unchecked")
    public static <T> T getContextAttribute(FacesContext context, String name) {
        return (T) context.getAttributes().get(name);
    }

    /**
     * Sets context attribute in specified FacesContext of specified name and value
     *
     * @param context FacesContext in which attribute should be added
     * @param name Name of attribute
     * @param value Value of attribute
     */
    public static void setContextAttribute(FacesContext context, String name, Object value) {
        context.getAttributes().put(name, value);
    }

    /**
     * Adds call back script to actual C3PartialViewContext
     *
     * @param ctx Actual FacesContext
     * @param script Script to be added
     */
    public static void addCallbackScript(FacesContext ctx, String script) {
        C3PartialViewContext.getCurrentInstance(ctx).addCallbackScript(script);
    }

    /**
     * Returns actual PartialViewContext instance
     *
     * @param ctx Actual FacesContext
     * @return Actual instance of C3PartialViewContext
     */
    public static C3PartialViewContext getPartialViewContext(FacesContext ctx) {
        return C3PartialViewContext.getCurrentInstance(ctx);
    }

    /**
     * Returns actual session map
     *
     * @param ctx Actual FacesContext
     * @return Actual session map
     */
    public static Map<String, Object> getSessionMap(FacesContext ctx) {
        return ctx.getExternalContext().getSessionMap();
    }

    /**
     * Returns actual view map
     *
     * @param ctx Actual FacesContext
     * @return Actual view map
     */
    public static Map<String, Object> getViewMap(FacesContext ctx) {
        return ctx.getViewRoot().getViewMap(true);
    }

    /**
     * Returns parameter map of actual request
     *
     * @param ctx Actual FacesContext
     * @return Parameter map of actual request
     */
    public static Map<String, String> getRequestParameterMap(FacesContext ctx) {
        return ctx.getExternalContext().getRequestParameterMap();
    }

    /**
     * Returns true if actual request is of AJAX type
     *
     * @param ctx Actual FacesContext
     * @return True if actual request is of AJAX type
     */
    public static boolean isAjaxRequest(FacesContext ctx) {
        return ctx.getPartialViewContext().isAjaxRequest();
    }
}
