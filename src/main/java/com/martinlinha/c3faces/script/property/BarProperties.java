package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;

/**
 *
 * @author Martin Linha
 */
public class BarProperties extends ObjectProp {

    public static String NAME = "bar";

    private Integer width;
    private Boolean zerobased;

    public BarProperties() {
    }

    public BarProperties(Integer width, Boolean zerobased) {
        this.width = width;
        this.zerobased = zerobased;
    }

    @Override
    protected void preScriptBuild() {
        if (width != null) {
            addChild(new ValueProp("width", width));
        }

        if (zerobased != null) {
            addChild(new ValueProp("zerobased", zerobased));
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
