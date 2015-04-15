package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;

/**
 *
 * @author Martin Linha
 */
public class Area extends ObjectProp {
    
    public static String NAME = "area";

    private Boolean zerobased;

    public Area() {
    }

    public Area(Boolean zerobased) {
        this.zerobased = zerobased;
    }

    @Override
    protected void preScriptBuild() {
        if (zerobased != null) {
            addChild(new ValueProp("zerobased", zerobased));
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Boolean getZerobased() {
        return zerobased;
    }

    public void setZerobased(Boolean zerobased) {
        this.zerobased = zerobased;
    }
}
