package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ValueProp;

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

        if (width != null) {
            addChild(new ValueProp("width", width));
        }
        if (title != null) {
            addChild(new ValueProp("title", title, true));
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
