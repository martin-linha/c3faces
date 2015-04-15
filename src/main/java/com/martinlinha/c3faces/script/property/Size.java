package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.listener.ChangeListener;
import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;
import java.util.Arrays;

/**
 *
 * @author Martin Linha
 */
public class Size extends ObjectProp {

    public static String NAME = "size";

    private Integer width;
    private Integer height;

    public Size() {
    }

    public Size(Integer width, Integer height, ChangeListener... listeners) {
        addListeners(Arrays.asList(listeners));
        this.width = width;
        this.height = height;
    }

    @Override
    protected void preScriptBuild() {
        if (width != null) {
            addChild(new ValueProp("width", width));
        }
        if (height != null) {
            addChild(new ValueProp("height", height));
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
        fire("width", width);
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        fire("height", height);
        this.height = height;
    }
}
