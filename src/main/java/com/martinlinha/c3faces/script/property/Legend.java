package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.listener.ChangeListener;
import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;
import java.util.Arrays;

public class Legend extends ObjectProp {

    public static String NAME = "legend";

    private Legend.Position position;
    private Boolean show;
    private Boolean hide;
    private Legend.InsetAnchor insetAnchor;
    private Integer insetX;
    private Integer insetY;
    private Integer insetStep;
    private OnclickMethod itemOnclick;
    private OnmouseoverMethod itemOnmouseover;
    private OnmouseoutMethod itemOnmouseout;

    public enum Position {

        BOTTOM, RIGHT, INSET
    }

    public enum InsetAnchor {

        TOPLEFT, TOPRIGHT, BOTTOMLEFT, BOTTOMRIGHT
    }

    public Legend() {
    }

    public Legend(Position position, Boolean show, Boolean hide, InsetAnchor insetAnchor, Integer insetX, Integer insetY,
            Integer insetStep, OnclickMethod itemOnclick, OnmouseoverMethod itemOnmouseover,
            OnmouseoutMethod itemOnmouseout, ChangeListener... listeners) {
        addListeners(Arrays.asList(listeners));
        this.position = position;
        this.show = show;
        this.hide = hide;
        this.insetAnchor = insetAnchor;
        this.insetX = insetX;
        this.insetY = insetY;
        this.insetStep = insetStep;
        this.itemOnclick = itemOnclick;
        this.itemOnmouseover = itemOnmouseover;
        this.itemOnmouseout = itemOnmouseout;
    }

    @Override
    protected void preScriptBuild() {
        if (position != null) {
            addChild(new ValueProp("position", position.name().toLowerCase(), true));
        }
        if (show != null) {
            addChild(new ValueProp("show", show));
        }
        if (hide != null) {
            addChild(new ValueProp("hide", hide));
        }
        if (insetAnchor != null || insetX != null || insetY != null || insetStep != null) {
            ObjectProp obj = new ObjectProp();
            obj.setName("inset");

            if (insetAnchor != null) {
                switch (insetAnchor) {
                    case BOTTOMLEFT:
                        obj.addChild(new ValueProp("anchor", "bottom-left", true));
                        break;
                    case BOTTOMRIGHT:
                        obj.addChild(new ValueProp("anchor", "bottom-right", true));
                        break;
                    case TOPLEFT:
                        obj.addChild(new ValueProp("anchor", "top-left", true));
                        break;
                    case TOPRIGHT:
                        obj.addChild(new ValueProp("anchor", "top-right", true));
                        break;
                }
            }
            if (insetX != null) {
                obj.addChild(new ValueProp("x", insetX));
            }
            if (insetY != null) {
                obj.addChild(new ValueProp("y", insetY));
            }
            if (insetStep != null) {
                obj.addChild(new ValueProp("step", insetStep));
            }
            addChild(obj);
        }
        if (itemOnclick != null || itemOnmouseout != null || itemOnmouseover != null) {
            ObjectProp obj = new ObjectProp();
            obj.setName("item");

            if (itemOnclick != null) {
                obj.addChild(itemOnclick);
            }
            if (itemOnmouseout != null) {
                obj.addChild(itemOnmouseout);
            }
            if (itemOnmouseover != null) {
                obj.addChild(itemOnmouseover);
            }
            addChild(obj);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        fire("legendShow", show);
        this.show = show;
    }

    public Boolean getHide() {
        return hide;
    }

    public void setHide(Boolean hide) {
        fire("legendHide", hide);
        this.hide = hide;
    }

    public InsetAnchor getInsetAnchor() {
        return insetAnchor;
    }

    public void setInsetAnchor(InsetAnchor insetAnchor) {
        this.insetAnchor = insetAnchor;
    }

    public Integer getInsetX() {
        return insetX;
    }

    public void setInsetX(Integer insetX) {
        this.insetX = insetX;
    }

    public Integer getInsetY() {
        return insetY;
    }

    public void setInsetY(Integer insetY) {
        this.insetY = insetY;
    }

    public Integer getInsetStep() {
        return insetStep;
    }

    public void setInsetStep(Integer insetStep) {
        this.insetStep = insetStep;
    }

    public OnclickMethod getItemOnclick() {
        return itemOnclick;
    }

    public void setItemOnclick(OnclickMethod itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    public OnmouseoverMethod getItemOnmouseover() {
        return itemOnmouseover;
    }

    public void setItemOnmouseover(OnmouseoverMethod itemOnmouseover) {
        this.itemOnmouseover = itemOnmouseover;
    }

    public OnmouseoutMethod getItemOnmouseout() {
        return itemOnmouseout;
    }

    public void setItemOnmouseout(OnmouseoutMethod itemOnmouseout) {
        this.itemOnmouseout = itemOnmouseout;
    }
}
