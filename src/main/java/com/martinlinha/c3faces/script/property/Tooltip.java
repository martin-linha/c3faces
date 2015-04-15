package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;

/**
 *
 * @author Martin Linha
 */
public class Tooltip extends ObjectProp {

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
            addChild(new ValueProp("show", show));
        }
        if (grouped != null) {
            addChild(new ValueProp("grouped", grouped));
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
