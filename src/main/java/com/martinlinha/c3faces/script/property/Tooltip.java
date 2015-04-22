package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 * Through this class is possible to define chart's visual properties, specifically visual properties of chart's tooltip.
 *
 *
 * @see http://c3js.org/reference.html for attrs info
 * @author Martin Linha
 */
public class Tooltip extends ObjectBlock {

    public static String NAME = "tooltip";

    private Boolean show;
    private Boolean grouped;

    public Tooltip() {
    }

    public Tooltip(Boolean show, Boolean grouped) {
        this.show = show;
        this.grouped = grouped;
    }

    @Override
    protected void preScriptBuild() {
        if (show != null) {
            addChild(new ValueBlock("show", show));
        }
        if (grouped != null) {
            addChild(new ValueBlock("grouped", grouped));
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

    public Boolean getGrouped() {
        return grouped;
    }

    public void setGrouped(Boolean grouped) {
        this.grouped = grouped;
    }
}
