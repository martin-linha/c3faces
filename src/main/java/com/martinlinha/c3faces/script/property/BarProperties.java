package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 *
 * @author Martin Linha
 */
public class BarProperties extends ObjectBlock {

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
            addChild(new ValueBlock("width", width));
        }

        if (zerobased != null) {
            addChild(new ValueBlock("zerobased", zerobased));
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Boolean getZerobased() {
        return zerobased;
    }

    public void setZerobased(Boolean zerobased) {
        this.zerobased = zerobased;
    }
}
