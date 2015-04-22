package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ArrayBlock;
import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 * Through this class is possible to define chart's visual properties, specifically defines the behavior of chart's zoom capabilities.
 *
 * @author Martin Linha
 */
public class Zoom extends ObjectBlock {

    public static String NAME = "zoom";

    private Boolean enabled;
    private Boolean rescale;
    private Integer extentFrom;
    private Integer extentTo;
    private OnzoomMethod onzoomMethodProp;

    public Zoom() {
    }

    public Zoom(Boolean enabled, Boolean rescale, Integer extentFrom, Integer extentTo, OnzoomMethod onzoomMethodProp) {
        this.enabled = enabled;
        this.rescale = rescale;
        this.extentFrom = extentFrom;
        this.extentTo = extentTo;
        this.onzoomMethodProp = onzoomMethodProp;
    }

    @Override
    protected void preScriptBuild() {
        if (enabled != null) {
            addChild(new ValueBlock("enabled", enabled));
        }
        if (rescale != null) {
            addChild(new ValueBlock("rescale", rescale));
        }

        if (extentFrom != null && extentTo != null) {
            addChild(new ValueBlock("extent", new ArrayBlock(extentFrom + ", " + extentTo)));
        }

        if (onzoomMethodProp != null) {
            addChild(onzoomMethodProp);
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

    public Boolean getRescale() {
        return rescale;
    }

    public void setRescale(Boolean rescale) {
        this.rescale = rescale;
    }

    public Integer getExtentFrom() {
        return extentFrom;
    }

    public void setExtentFrom(Integer extentFrom) {
        this.extentFrom = extentFrom;
    }

    public Integer getExtentTo() {
        return extentTo;
    }

    public void setExtentTo(Integer extentTo) {
        this.extentTo = extentTo;
    }

    public OnzoomMethod getOnzoomMethodProp() {
        return onzoomMethodProp;
    }

    public void setOnzoomMethodProp(OnzoomMethod onzoomMethodProp) {
        this.onzoomMethodProp = onzoomMethodProp;
    }
}
