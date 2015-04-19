package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 *
 * @author Martin Linha
 */
public class Area extends ObjectBlock {
    
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
            addChild(new ValueBlock("zerobased", zerobased));
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
