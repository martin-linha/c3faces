package com.martinlinha.c3faces.component;

import com.martinlinha.c3faces.component.property.C3Property;
import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.modifier.Colors;
import com.martinlinha.c3faces.script.modifier.Load;
import com.martinlinha.c3faces.script.modifier.Names;
import com.martinlinha.c3faces.script.modifier.Transform;
import com.martinlinha.c3faces.script.modifier.TransformTypes;
import com.martinlinha.c3faces.script.property.Bindto;
import com.martinlinha.c3faces.script.property.Data;
import com.martinlinha.c3faces.script.property.OnclickMethod;
import com.martinlinha.c3faces.util.JSBuilder;
import com.martinlinha.c3faces.util.JSTools;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialResponseWriter;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.ConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin Linha
 */
@ResourceDependencies({
    @ResourceDependency(library = "c3faces", name = "d3.min.js"),
    @ResourceDependency(library = "c3faces", name = "d3.js"),
    @ResourceDependency(library = "c3faces", name = "c3.min.js"),
    @ResourceDependency(library = "c3faces", name = "c3.css"),
    @ResourceDependency(library = "c3faces", name = "jquery-2.1.3.min.js")
})

public abstract class C3Chart extends UIInput implements ClientBehaviorHolder {

    private final C3Manager manager;
    private Data data;
    private HtmlInputHidden hiddenIndexHolder;
    private String javaScriptVar;
    private String divId;

    // Components request-resist attributes
    private enum PropertyKeys {

        data, cssClass, style, componentProperties
    }

    // Constants and default values ------------------------------------------------------------------
    private static final String DIV_APPENDER = "chart";
    private static final Logger LOGGER = LoggerFactory.getLogger(C3Chart.class);
    private static final String HIDDEN_NAME = "Index";
    private static final String ATTR_DATA = "data";

    // Basic settings ------------------------------------------------------------------------------
    public C3Chart() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if (externalContext.getSessionMap().get(C3Manager.SESSION_KEY) == null) {
            externalContext.getSessionMap().put(C3Manager.SESSION_KEY, new C3Manager());
        }
        manager = (C3Manager) externalContext.getSessionMap().get(C3Manager.SESSION_KEY);
    }

    @Override
    public Collection<String> getEventNames() {
        return Arrays.asList("click");
    }

    protected ChartType getChartType() {
        return null;
    }

    // Abstract methods
    public abstract List<Property> getDefaultProperties();

    /**
     * Return default event name. Default points to "click"
     *
     * @return click
     */
    @Override
    public String getDefaultEventName() {
        return "click";
    }

    @Override
    public final String getRendererType() {
        return null;
    }

    // Component phase activities definition ------------------------------------------------------------
    // Decode phase ------------------------------------------------------------
    // from https://www.java.net/node/697093
    @Override
    public final void decode(FacesContext context) {
        Map<String, List<ClientBehavior>> behaviors = getClientBehaviors();
        if (behaviors.isEmpty()) {
            return;
        }

        ExternalContext external = context.getExternalContext();
        Map<String, String> params = external.getRequestParameterMap();
        String behaviorEvent = params.get("javax.faces.behavior.event");

        if (behaviorEvent != null) {
            List<ClientBehavior> behaviorsForEvent = behaviors.get(behaviorEvent);

            if (behaviors.size() > 0) {
                String behaviorSource = params.get("javax.faces.source");
                String clientId = getClientId(context);
                if (null != behaviorSource && behaviorSource.equals(clientId)) {
                    for (ClientBehavior behavior : behaviorsForEvent) {
                        behavior.decode(context, this);
                    }
                }
            }
        }
        setSubmittedValue(context.getExternalContext().getRequestParameterMap().get(getClientId() + HIDDEN_NAME));
    }

    // Encode phase -------------------------------------------------------------------------------------
    @Override
    public void encodeBegin(FacesContext context) throws IOException {

        assignDataWithListeners();

        if (!context.getPartialViewContext()
                .isAjaxRequest()) {
            hiddenIndexHolder = (HtmlInputHidden) context.getApplication().createComponent(HtmlInputHidden.COMPONENT_TYPE);
            hiddenIndexHolder.setId(getId() + HIDDEN_NAME);
            this.getChildren().add(hiddenIndexHolder);
            hiddenIndexHolder.encodeAll(context);

            if (getChartType() != null) {
                getComponentProperties().setChartType(getChartType());
            }
        } else {
            hiddenIndexHolder = (HtmlInputHidden) context.getViewRoot().findComponent(getClientId() + HIDDEN_NAME);
        }

        javaScriptVar = JSTools.jsName(getClientId());
        divId = getId() + DIV_APPENDER;
        List<Property> customProperties = getDefaultProperties();

        resolveFaceletProperties();
        if (customProperties
                != null) {
            getComponentProperties().addProperties(customProperties);
        }
        String ajaxAction = getAjaxAction(context);

        String onclickScript = JSTools.semicolonSeparatedStatements("var event = 'click'",
                "console.log(" + javaScriptVar + ".selected()); if (" + javaScriptVar + ".selected().length == 0) {"
                + "$(\"input[name='" + hiddenIndexHolder.getClientId() + "']" + "\").attr('value', '');\n"
                + ajaxAction + "; } else {"
                + "$(\"input[name='" + hiddenIndexHolder.getClientId() + "']" + "\").attr('value', d.id);\n"
                + ajaxAction
                + "}");

        addDefaultProperties(onclickScript);

        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("script", this);
        writer.writeAttribute("id", getClientId(), "id");

        writer.endElement("script");

        writer.startElement("div", this);
        writer.writeAttribute("id", getDivId(), "id");
        writer.writeAttribute("class", getCssClass(), "class");
        writer.writeAttribute("style", getStyle(), "style");
        writer.endElement("div");

        super.encodeBegin(context);
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {

        if (!context.getPartialViewContext().isAjaxRequest()) {
            ResponseWriter writer = context.getResponseWriter();

            writer.startElement("script", this);
            writer.write(JSBuilder.build().var(javaScriptVar).c3().generate(getComponentProperties().getProperties()).endLine().getResult());
            writer.endElement("script");
        }

        if (context.getPartialViewContext().isAjaxRequest()) {
            PartialResponseWriter pWriter = context.getPartialViewContext().getPartialResponseWriter();
            if (!manager.isDataChanged()) {
                pWriter.endUpdate();
                pWriter.startEval();
                pWriter.write(JSTools.semicolonSeparatedModifierScript(getComponentProperties().getPropertyModifiers(), javaScriptVar));
                pWriter.endEval();
                pWriter.startUpdate(getClientId());
//                pWriter.endDocument();
//                pWriter.flush();
//                pWriter.close();
                context.responseComplete();
            } else {
                pWriter.endUpdate();
                pWriter.startEval();
                pWriter.write(JSBuilder.build().var(javaScriptVar).c3().generate(getComponentProperties().getProperties()).endLine().getResult());
                pWriter.endEval();
                pWriter.startUpdate(getClientId());
                context.responseComplete();
            }
        }
        getComponentProperties().resetListeners();
    }

    @Override
    protected Object getConvertedValue(FacesContext context, Object newSubmittedValue) throws ConverterException {

        assignDataWithListeners();

        String uuid = (String) context.getExternalContext().getRequestParameterMap().get(getClientId() + HIDDEN_NAME);
        if (data != null) {
            return data.getDataSetById(uuid);
        }
        return null;
    }

    // Helpers --------------------------------------------------------------------------------------------------------
    /**
     * Returns .js script for ajax call
     *
     * @see jsf.js
     * @param context
     * @return JS script for ajax action
     */
    public String getAjaxAction(FacesContext context) {
        Map<String, List<ClientBehavior>> behaviors = getClientBehaviors();

        ClientBehaviorContext behaviorContext
                = ClientBehaviorContext.createClientBehaviorContext(context,
                        this, "click", getClientId(context), null);
        String ajaxAction = "";

        if (behaviors.containsKey("click")) {
            ajaxAction = behaviors.get("click").get(0).getScript(behaviorContext);
        }
        return ajaxAction;
    }

    private void resolveFaceletProperties() {
        for (UIComponent comp : getChildren()) {
            if (comp instanceof C3Property) {
                C3Property prop = (C3Property) comp;
                getComponentProperties().addProperty(prop.getAssociatedProperty());
            }
        }
    }

    private void addDefaultProperties(String onClickScript) {
        String typeOnclick = "jsfOnunselectScript";
//        getComponentProperties().getComponentData().removeChildrenByType(type);

        OnclickMethod onClickMethod = new OnclickMethod(onClickScript, "d", "i");
        getComponentProperties().getComponentData().addChild(onClickMethod);

        Bindto bindto = (Bindto) getComponentProperties().getProperty("bindto");
        if (bindto != null) {
            bindto.setBody("#" + divId);
        } else {
            getComponentProperties().addProperty(new Bindto("#" + divId));
        }
    }

    /**
     * Return specified attribute value or default if it's null
     */
    @SuppressWarnings("unchecked")
    private <T> T
            getAttributeValue(String key, T defaultValue
            ) {
        T value = (T) getAttributes().get(key);
        return (value != null) ? value : defaultValue;
    }

    private Integer assignValue(String facelet, Integer val2, Integer def) {
        if (getAttributes().get(facelet) != null) {
            return Integer.parseInt((String) getAttributes().get(facelet));
        }
        if (val2 != null) {
            return val2;
        }
        return def;
    }

    private void assignDataWithListeners() {
        if (getAttributes().get(ATTR_DATA) != null) {
            data = (Data) getAttributes().get(ATTR_DATA);
        } else {
            data = new Data();
        }
        manager.addData(getClientId(), data);

        if (manager.isDataChanged()) {
            getComponentProperties().clearProperties();

            Modifier dataModifier = new Load();
            dataModifier.addModifier(new Names());
            dataModifier.addModifier(new Colors());
            dataModifier.addModifier(new Transform());
            dataModifier.addModifier(new TransformTypes());
            data.addListener(dataModifier);
        }

        getComponentProperties().addProperty(data);
    }

    // Getters & setters ----------------------------------------------------------------------------------------------
    public String getDivId() {
        return divId;
    }

    public void setCssClass(String cssClass) {
        getStateHelper().put(PropertyKeys.cssClass, cssClass);
    }

    public String getCssClass() {
        return (String) getStateHelper().eval(PropertyKeys.cssClass);
    }

    public void setStyle(String style) {
        getStateHelper().put(PropertyKeys.style, style);
    }

    public String getStyle() {
        return (String) getStateHelper().eval(PropertyKeys.style);
    }

    public void setComponentProperties(ComponentProperties componentProperties) {
        getStateHelper().put(PropertyKeys.componentProperties, componentProperties);
    }

    public final ComponentProperties getComponentProperties() {
        if ((ComponentProperties) getStateHelper().get(PropertyKeys.componentProperties) == null) {
            ComponentProperties componentProperties = new ComponentProperties();
            setComponentProperties(componentProperties);
        }
        return (ComponentProperties) getStateHelper().get(PropertyKeys.componentProperties);
    }
}
