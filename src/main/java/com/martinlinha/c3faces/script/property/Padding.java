package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;

/**
 *
 * @author Martin Linha
 */
public class Padding extends ObjectProp {

    public static String NAME = "padding";

    private Integer top;
    private Integer right;
    private Integer left;
    private Integer bottom;

    public Padding() {
    }

    public Padding(Integer top, Integer right, Integer left, Integer bottom) {
        this.top = top;
        this.right = right;
        this.left = left;
        this.bottom = bottom;
    }

    @Override
    protected void preScriptBuild() {
        if (top != null) {
            addChild(new ValueProp("top", top));
        }
        if (right != null) {
            addChild(new ValueProp("right", right));
        }
        if (left != null) {
            addChild(new ValueProp("left", left));
        }
        if (bottom != null) {
            addChild(new ValueProp("bottom", bottom));
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getBottom() {
        return bottom;
    }

    public void setBottom(Integer bottom) {
        this.bottom = bottom;
    }
}
