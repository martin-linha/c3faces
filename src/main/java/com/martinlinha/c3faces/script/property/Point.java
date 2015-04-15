package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;

/**
 *
 * @author Martin Linha
 */
public class Point extends ObjectProp {
    
    public static String NAME = "point";

    private Boolean show;
    private Double r;
    private Boolean expand;
    private Double expandR;
    private Double selectR;

    public Point() {
    }

    public Point(Boolean show, Double r, Boolean expand, Double expandR, Double selectR) {
        this.show = show;
        this.r = r;
        this.expand = expand;
        this.expandR = expandR;
        this.selectR = selectR;
    }

    @Override
    public void preScriptBuild() {
        if (show != null) {
            addChild(new ValueProp("show", show));
        }
        if (r != null) {
            addChild(new ValueProp("r", r));
        }
        if (expand != null) {
            addChild(new ObjectProp("focus", new ObjectProp("expand", new ValueProp("enabled", expand))));
        }
        if (expandR != null) {
            addChild(new ObjectProp("focus", new ObjectProp("expand", new ValueProp("r", expandR))));
        }
        if (selectR != null) {
            addChild(new ObjectProp("select", new ValueProp("r", selectR)));
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

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public Double getExpandR() {
        return expandR;
    }

    public void setExpandR(Double expandR) {
        this.expandR = expandR;
    }

    public Double getSelectR() {
        return selectR;
    }

    public void setSelectR(Double selectR) {
        this.selectR = selectR;
    }
}
