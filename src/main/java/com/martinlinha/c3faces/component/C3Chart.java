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
import com.martinlinha.c3faces.script.property.Transition;
import com.martinlinha.c3faces.util.Faces;
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
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.ConverterException;

/**
 * The main abstract class responsible for implementation of basic behavior of C3.js based charts.
 *
 * Component holds an editable value (interface EditableValueHolder and scripts (interface ClientBehaviorHolder). Submitted value is get through
 * hidden input field, which this component also renders.
 *
 * Class includes dependencies required for C3.js chart drawing.
 *
 * @author Martin Linha
 * @see <a href="http://c3faces.martinlinha.com">http://c3faces.martinlinha.com</a>
 */
@ResourceDependencies({
    @ResourceDependency(library = "c3faces", name = "d3.min.js"),
    @ResourceDependency(library = "c3faces", name = "d3.js"),
    @ResourceDependency(library = "c3faces", name = "c3.min.js"),
    @ResourceDependency(library = "c3faces", name = "c3.css")
})

public abstract class C3Chart extends UIInput implements ClientBehaviorHolder {

    private final C3Manager manager;
    private Data data;

    // Components request-resist attributes ------------------------------------------------------------------
    private enum PropertyKeys {

        data, cssClass, style, componentProperties, lastGeneratedScript
    }

    // Constants and default values ------------------------------------------------------------------
    private static final String HIDDEN_NAME = "Index";
    private static final String ATTR_DATA = "data";
    private static final String EVENT_NAME = "click";

    // Basic settings ------------------------------------------------------------------------------
    /**
     * Constructor is responsible for putting a C3Manager.class instance to session, if not exists.
     */
    public C3Chart() {
        Map<String, Object> sessionMap = Faces.getSessionMap(FacesContext.getCurrentInstance());
        sessionMap.putIfAbsent(C3Manager.SESSION_KEY, new C3Manager());
        manager = (C3Manager) sessionMap.get(C3Manager.SESSION_KEY);
    }

    /**
     * An override for UIComponent to tell JSF that component is expecting only "click" event represented by clicking on chart's value. This method
     * should not be overriden.
     *
     * @return Collection of event names
     */
    @Override
    public Collection<String> getEventNames() {
        return Arrays.asList(EVENT_NAME);
    }

    /**
     * Takes the returned value and set it as default chart type. Must conform to C3.js specification or generates JS error.
     *
     * @return ChartType
     */
    protected ChartType getChartType() {
        return null;
    }

    // Abstract methods ------------------------------------------------------------------------------
    /**
     * List of properties that will be added as a default properties of the chart, i.e. properties that will be always rendered.
     *
     * @return ChartType
     */
    public abstract List<Property> getDefaultProperties();

    /**
     * Return default event name, i.e. "click". This method should not be overriden.
     *
     * @return event name
     */
    @Override
    public String getDefaultEventName() {
        return EVENT_NAME;
    }

    /**
     * As a basic settings, the component will render itself. Override to allow another Renderer.
     *
     * @return Renderer type responsible for component render
     */
    @Override
    public final String getRendererType() {
        return null;
    }

    // Component phase activities definition ------------------------------------------------------------
    // Decode phase ------------------------------------------------------------
    /**
     * Method checks if ajax behavior is supplied and if so, decodes it. Also checks for hidden input field value whith selected chart value. If
     * exists, set as submitted value of this component (which will be converted later).
     *
     * IMPORTANT! In case of override, call back this method on super type!
     *
     * @param context Current FacesContext instance
     */
    @Override
    public void decode(FacesContext context) {
        Map<String, List<ClientBehavior>> behaviors = getClientBehaviors();
        if (behaviors.isEmpty()) {
            return;
        }

        Map<String, String> params = Faces.getRequestParameterMap(context);
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

        setSubmittedValue(params.get(getClientId() + HIDDEN_NAME));
    }

    // Encode phase -------------------------------------------------------------------------------------
    /**
     * Method checks if ajax behavior is supplied and if so, decodes it. Also checks for hidden input field value whith selected chart value. If
     * exists, set as submitted value of this component (which will be converted later).
     *
     * IMPORTANT! In case of override, call back this method on super type!
     *
     * @param context Current FacesContext instance
     * @throws java.io.IOException
     */
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        assignData();
        resolveFaceletProperties();
        getComponentProperties().addProperties(getDefaultProperties());
        addBasicProperties(context);

        writer.startElement("div", this);
        writer.writeAttribute("id", getClientId(), "id");
        writer.writeAttribute("name", getClientId(), "name");
        writer.writeAttribute("class", getCssClass(), "class");
        writer.writeAttribute("style", getStyle(), "style");

        super.encodeBegin(context);
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        boolean chartExists = Faces.getRequestParameterMap(context).containsKey(getClientId() + HIDDEN_NAME);
        String newGeneratedScript = JSBuilder.build().var(getFixedJsVar()).c3().generate(getComponentProperties().getProperties()).endLine().getResult();
        String lastGeneratedScript = getLastGeneratedScript();
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("script", this);
        if (chartExists && Faces.isAjaxRequest(context)) {
            String modificationScript = JSTools.semicolonSeparatedModifierScript(getComponentProperties().getPropertyModifiers(), getFixedJsVar());

            writer.write(lastGeneratedScript);
            Faces.addCallbackScript(context, getFixedJsVar() + ".internal.loadConfig({\n"
                    + "    transition: {\n"
                    + "        duration: 350\n"
                    + "    }\n"
                    + "});");
            Faces.addCallbackScript(context, modificationScript);
        } else {
            writer.write(newGeneratedScript);
        }
        writer.endElement("script");
        writer.endElement("div");
        encodeHiddenInput(writer);

        setLastGeneratedScript(newGeneratedScript);
    }

    @Override
    protected Object getConvertedValue(FacesContext context, Object newSubmittedValue) throws ConverterException {
        assignData();
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

    private String getFixedJsVar() {
        return JSTools.jsName(getClientId());
    }

    private String getOnclickScript(FacesContext context) {
        String ajaxAction = getAjaxAction(context);

        return JSTools.semicolonSeparatedStatements("var event = 'click'",
                "if (" + getFixedJsVar() + ".selected().length == 0) {"
                + "document.getElementsByName('" + getClientId() + HIDDEN_NAME + "')[0].value = '';\n"
                + ajaxAction + "; } else {"
                + "document.getElementsByName('" + getClientId() + HIDDEN_NAME + "')[0].value = d.id;\n"
                + ajaxAction
                + "}");
    }

    private void resolveFaceletProperties() {
        for (UIComponent comp : getChildren()) {
            if (comp instanceof C3Property) {
                C3Property prop = (C3Property) comp;
                getComponentProperties().addProperty(prop.getAssociatedProperty());
            }
        }
    }

    private void addBasicProperties(FacesContext context) {
        OnclickMethod onClickMethod = new OnclickMethod(getOnclickScript(context), "d", "i");
        getComponentProperties().getComponentData().addChild(onClickMethod);

        Bindto bindto = (Bindto) getComponentProperties().getProperty("bindto");
        if (bindto != null) {
            bindto.setBody("#" + JSTools.colonAid(getClientId()));
        } else {
            getComponentProperties().addProperty(new Bindto("#" + JSTools.colonAid(getClientId())));
        }
        getComponentProperties().addProperty(new Transition(0));
    }

    private void assignData() {
        if (getAttributes().get(ATTR_DATA) != null) {
            data = (Data) getAttributes().get(ATTR_DATA);
        } else {
            data = new Data();
        }
        if (data.getChartType() == null && getChartType() != null) {
            data.setChartType(getChartType());
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

    private void encodeHiddenInput(ResponseWriter writer) throws IOException {
        writer.startElement("input", this);
        writer.writeAttribute("type", "hidden", "type");
        writer.writeAttribute("name", getClientId() + HIDDEN_NAME, "name");
        writer.writeAttribute("value", "", "value");
        writer.endElement("input");
    }

    // Getters & setters ----------------------------------------------------------------------------------------------
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

    public void setLastGeneratedScript(String style) {
        getStateHelper().put(PropertyKeys.lastGeneratedScript, style);
    }

    public String getLastGeneratedScript() {
        return (String) getStateHelper().get(PropertyKeys.lastGeneratedScript);
    }

    public void resetState() {
        getComponentProperties().resetListeners();
    }
}
