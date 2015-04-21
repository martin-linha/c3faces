package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 *
 * @author Martin Linha
 */
public class Size extends ObjectBlock {

    public static final String EVENT_WIDTH_CHANGED = "sizeWidthChanged";
    public static final String EVENT_HEIGHT_CHANGED = "sizeHeightChanged";

    public static String NAME = "size";

    private Integer width;
    private Integer height;

    public Size() {
    }

    public Size(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected void preScriptBuild() {
        if (width != null) {
            addChild(new ValueBlock("width", width));
        }
        if (height != null) {
            addChild(new ValueBlock("height", height));
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
        fire(EVENT_WIDTH_CHANGED, width);
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        fire(EVENT_HEIGHT_CHANGED, height);
        this.height = height;
    }
}
