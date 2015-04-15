package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;

/**
 *
 * @author Martin Linha
 */
public class Interaction extends ObjectProp {

    public static String NAME = "interaction";

    private Boolean enabled;

    public Interaction() {
    }

    public Interaction(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    protected void preScriptBuild() {
        if (enabled != null) {
            addChild(new ValueProp("enabled", enabled));
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
