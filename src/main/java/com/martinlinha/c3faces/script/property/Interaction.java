package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 * Through this class is possible to specify if interaction of chart should be enabled. Mainly used for internal reasons.
 *
 *
 * @author Martin Linha
 */
public class Interaction extends ObjectBlock {

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
            addChild(new ValueBlock("enabled", enabled));
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
