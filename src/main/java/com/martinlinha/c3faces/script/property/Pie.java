package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;

/**
 *
 * @author Martin Linha
 */
public class Pie extends ObjectProp {

    public static String NAME = "pie";

    private Boolean expand;
    private Boolean showLabels;

    public Pie() {
    }

    public Pie(Boolean expand, Boolean showLabels) {
        this.expand = expand;
        this.showLabels = showLabels;
    }

    @Override
    protected void preScriptBuild() {
        if (expand != null) {
            addChild(new ValueProp("expand", expand));
        }

        if (showLabels != null) {
            addChild(new ObjectProp("label", new ValueProp("show", showLabels)));
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public Boolean getShowLabels() {
        return showLabels;
    }

    public void setShowLabel(Boolean showLabels) {
        this.showLabels = showLabels;
    }
}
