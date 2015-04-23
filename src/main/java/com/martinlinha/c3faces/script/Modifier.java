package com.martinlinha.c3faces.script;

import com.martinlinha.c3faces.listener.PropertyModifier;
import com.martinlinha.c3faces.listener.change.Change;
import com.martinlinha.c3faces.listener.change.Changes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Objects of this class are used to generate scripts which dynamically changes visual properties of charts. Created scripts are in respect of C3.js
 * API syntax.
 *
 * Each instance is composite, ie. it contains other objects of same type.
 *
 * @author Martin Linha
 */
public abstract class Modifier extends Changes implements PropertyModifier {

    private List<String> fields;
    private final List<Modifier> modifiers = new ArrayList<>();
    private boolean successfullyGeneratedScript;
    private final int duration = 500;
    private final boolean timeoutable = true;

    /**
     * Constructs new Modifier object with multiple field names. Fields are names which are written before method function name. Output example:
     *
     * FIELD1.FIELD2.count( 1+2 )
     *
     * @param fields Field to be added before function name
     */
    public Modifier(String... fields) {
        this.fields = Arrays.asList(fields);
    }

    /**
     * Abstract method which returns instance of property which script is included in body of generated modification script.
     *
     * @return Property which script is included in body
     */
    protected abstract Property getModificationProperty();

    /**
     * Returns name of generated .js script. If Javascript variable of rendered chart is "chart", output will looks like following example.
     *
     * chart.METHODNAME( 1+2 )
     *
     * @return Function name of generated .js script
     */
    protected abstract String getMethodName();

    /**
     * Returns script in respect of C3.js API syntax. Iterates over all composite children and call on them same method to properly generate complete
     * code structure.
     *
     * Takes parent name, which represents Javascript variable of chart. Duration is time in millis of animation rendering on client.
     *
     * @param parent Javascript variable of chart
     * @param duration Duration of animation in millis
     * @return C3.js script to dynamically modify chart on client
     */
    @Override
    public final String getScript(String parent, int duration) {
        StringBuilder sb = new StringBuilder();
        String script = generateScript(parent);
        if (!script.isEmpty()) {
            if (isTimeoutable()) {
                sb.append("setTimeout(function () {");
                sb.append(script);
                sb.append("}, ");
                sb.append(duration);
                sb.append(")");
            } else {
                sb.append(script);
            }
            sb.append("; \n");
            duration += getDuration();
        }
        for (Modifier mod : modifiers) {
            sb.append(mod.getScript(parent, duration));
            if (mod.isSuccessfullyGeneratedScript()) {
                duration += mod.getDuration();
            }
        }
        return sb.toString();
    }

    /**
     * Method which clears all objects representing changes made in observable objects. Saem method is also called in all composite children.
     *
     */
    @Override
    public void reset() {
        getChangedProps().clear();
        setSuccessfullyGeneratedScript(false);
        for (Modifier mod : modifiers) {
            mod.reset();
        }
    }

    /**
     * Specifies if generated script will include name of Property which is included in body of generated modifier script.
     *
     * @return True if generated script should include name of Property object
     */
    public boolean includePropName() {
        return false;
    }

    /**
     * When returns true generated script won't have body. Output example:
     *
     * chart.method()
     *
     * @return True if generated script should contain no body
     */
    public boolean isMethod() {
        return false;
    }

    /**
     * Adds specified elements to object's children collection
     *
     * @param modifier Modifier to be added to composite collection
     * @return Reference to the actual object of this class, ie. this method can be chained
     */
    public Modifier addModifier(Modifier modifier) {
        modifiers.add(modifier);
        return this;
    }

    /**
     * This method is invoked to notify all listeners. It propagates the invocation of same method to all composite children of this class.
     *
     * @param change Object of change
     */
    @Override
    public void onChange(Change<?> change) {
        super.onChange(change);
        for (Modifier m : modifiers) {
            m.onChange(change);
        }
    }

    /**
     * Return total duration in millis. If this object have any children objects of same type, increment total duration by their duration.
     *
     * @return Sum of durations including composite children of this object
     */
    @Override
    public int totalDuration() {
        int dur = 0;
        if (isSuccessfullyGeneratedScript()) {
            dur += getDuration();
        }
        if (isComposite()) {
            for (Modifier mod : modifiers) {
                dur += mod.totalDuration();
            }
        }
        return dur;
    }

    /**
     * Returns duration of .js animation of this class instance.
     *
     * @return Duration of .js animation of this class instance
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns all associated field names.
     *
     * @return All associated field names
     */
    public List<String> getFields() {
        return fields;
    }

    /**
     * Sets collection of associated fields.
     *
     * @param fields Collection of associated fields
     */
    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    /**
     * Return true if this object contains any composite children.
     *
     * @return True if this object contains any composite children
     */
    public boolean isComposite() {
        return modifiers.size() > 0;
    }

    /**
     * Returns object's children collection
     *
     * @return Object's children collection
     */
    public List<Modifier> getModifiers() {
        return modifiers;
    }

    /**
     * Returns true if this object has been already generated any script.
     *
     * @return True if this object has been already generated any script
     */
    public boolean isSuccessfullyGeneratedScript() {
        return successfullyGeneratedScript;
    }

    /**
     * Sets boolean indicates if script of this object has been already generated.
     *
     * @param successfullyGeneratedScript Boolean indicates if script of this object has been already generated
     */
    public void setSuccessfullyGeneratedScript(boolean successfullyGeneratedScript) {
        this.successfullyGeneratedScript = successfullyGeneratedScript;
    }

    /**
     * If true, generated script will be nested in setTimeout( BODY, DURATION ) block.
     *
     * @return True if script should be nested in setTimeout() block
     */
    public boolean isTimeoutable() {
        return timeoutable;
    }

    private String generateScript(String parent) {
        StringBuilder sb = new StringBuilder();
        if (parent == null) {
            return "";
        }
        String propScript = getModificationProperty().getScript(includePropName());
        if ((propScript == null || propScript.isEmpty()) && !isMethod()) {
            return "";
        }
        sb.append(parent);
        if (getFields() != null && !getFields().isEmpty()) {
            for (int i = 0; i < getFields().size(); i++) {
                sb.append(".");
                sb.append(getFields().get(i));
            }
        }
        sb.append(".");
        sb.append(getMethodName());
        sb.append("(");
        sb.append(propScript);
        sb.append(")");

        String result = sb.toString();

        successfullyGeneratedScript = !result.isEmpty();
        return result;
    }
}
