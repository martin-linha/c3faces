package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 * Through this class is possible to define chart's visual properties, specifically Area visual properties.
 *
 *
 * @see http://c3js.org/reference.html for attrs info
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
