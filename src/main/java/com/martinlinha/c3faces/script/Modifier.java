package com.martinlinha.c3faces.script;

import com.martinlinha.c3faces.listener.change.Changes;
import com.martinlinha.c3faces.listener.change.Change;
import com.martinlinha.c3faces.listener.PropertyModifier;
import com.martinlinha.c3faces.script.Property;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public abstract class Modifier extends Changes implements PropertyModifier {

    private List<String> fields;
    private final List<Modifier> modifiers = new ArrayList<>();
    private boolean successfullyGeneratedScript;
    private final int duration = 500;
    private final boolean timeoutable = true;

    public Modifier() {
    }

    public Modifier(String... names) {
        this.fields = Arrays.asList(names);
    }

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

    @Override
    public void reset() {
        getChangedProps().clear();
        setSuccessfullyGeneratedScript(false);
        for (Modifier mod : modifiers) {
            mod.reset();
        }
    }

    protected abstract Property getModificationProperty();

    protected abstract String getMethodName();

    public boolean includePropName() {
        return false;
    }

    public boolean isMethod() {
        return false;
    }

    public Modifier addModifier(Modifier modifier) {
        modifiers.add(modifier);
        return this;
    }

    // propagate changes to children
    @Override
    public void onChange(Change<?> change) {
        super.onChange(change);
        for (Modifier m : modifiers) {
            m.onChange(change);
        }
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

    public int getDuration() {
        return duration;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public boolean isComposite() {
        return modifiers.size() > 0;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public boolean isSuccessfullyGeneratedScript() {
        return successfullyGeneratedScript;
    }

    public void setSuccessfullyGeneratedScript(boolean successfullyGeneratedScript) {
        this.successfullyGeneratedScript = successfullyGeneratedScript;
    }


    public boolean isTimeoutable() {
        return timeoutable;
    }
}
