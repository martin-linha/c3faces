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
        String positionString = "";
        String anchorString = "";
        if (position != null) {
            positionString = position.name().toLowerCase();
        }
        if (insetAnchor != null) {
            switch (insetAnchor) {
                case BOTTOMLEFT:
                    anchorString = "bottom-left";
                    break;
                case BOTTOMRIGHT:
                    anchorString = "bottom-right";
                    break;
                case TOPLEFT:
                    anchorString = "top-left";
                    break;
                case TOPRIGHT:
                    anchorString = "top-right";
                    break;
            }
        }

        addChild(new ValueProp("position", positionString, true));
        addChild(new ValueProp("show", show));
        addChild(new ValueProp("hide", hide));

        ObjectProp obj = new ObjectProp();
        obj.setName("inset");
        obj.addChild(new ValueProp("anchor", anchorString, true));
        obj.addChild(new ValueProp("x", insetX));
        obj.addChild(new ValueProp("y", insetY));
        obj.addChild(new ValueProp("step", insetStep));
        addChild(obj);

        ObjectProp item = new ObjectProp();
        item.setName("item");

        item.addChild(itemOnclick);
        item.addChild(itemOnmouseout);
        item.addChild(itemOnmouseover);
        addChild(item);
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
