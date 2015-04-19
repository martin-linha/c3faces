package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 *
 * @author Martin Linha
 */
public class Pie extends ObjectBlock {

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
        addChild(new ValueBlock("expand", expand));
        addChild(new ObjectBlock("label", new ValueBlock("show", showLabels)));
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
