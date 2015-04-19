package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 *
 * @author Martin Linha
 */
public class Subchart extends ObjectBlock {

    public static String NAME = "subchart";

    private Boolean show;
    private Integer height;
    private OnbrushMethod onbrushMethodProp;

    public Subchart() {
    }

    public Subchart(Boolean show, Integer height, OnbrushMethod onbrushMethodProp) {
        this.show = show;
        this.height = height;
        this.onbrushMethodProp = onbrushMethodProp;
    }

    @Override
    protected void preScriptBuild() {
        if (show != null) {
            addChild(new ValueBlock("show", show));
        }
        if (height != null) {
            addChild(new ObjectBlock("size", new ValueBlock("height", height)));
        }
        if (onbrushMethodProp != null) {
            addChild(onbrushMethodProp);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public OnbrushMethod getOnbrushMethodProp() {
        return onbrushMethodProp;
    }

    public void setOnbrushMethodProp(OnbrushMethod onbrushMethodProp) {
        this.onbrushMethodProp = onbrushMethodProp;
    }
}
