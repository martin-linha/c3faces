package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 *
 * @author Martin Linha
 */
public class DonutProperties extends Pie {

    public static String NAME = "donut";

    private Integer width;
    private String title;

    public DonutProperties() {
    }

    public DonutProperties(Integer width, String title, Boolean expand, Boolean showLabels) {
        super(expand, showLabels);
        this.width = width;
        this.title = title;
    }

    @Override
    protected void preScriptBuild() {
        addChild(new ValueBlock("width", width));
        addChild(new ValueBlock("title", title, true));
        addChild(new ValueBlock("expand", super.getExpand()));
        addChild(new ObjectBlock("label", new ValueBlock("show", super.getShowLabels())));
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
