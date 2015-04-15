package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ArrayProp;
import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Martin Linha
 */
public class GridProperties extends ObjectProp {

    public static String NAME = "grid";

    private Boolean showX;
    private Boolean showY;
    private Map<Double, String> additionalXLines = new HashMap<>();
    private Map<Double, String> additionalYLines = new HashMap<>();

    public GridProperties() {
    }

    public GridProperties(Boolean showX, Boolean showY, Map<Double, String> additionalXLines, Map<Double, String> additionalYLines) {
        this.showX = showX;
        this.showY = showY;
        this.additionalXLines = additionalXLines;
        this.additionalYLines = additionalYLines;
    }

    @Override
    protected void preScriptBuild() {
        if (showX != null) {
            addChild(new ObjectProp("x", new ValueProp("show", showX)));
        }

        if (showY != null) {
            addChild(new ObjectProp("y", new ValueProp("show", showY)));
        }

        if (additionalXLines != null) {
            ObjectProp wrapper = new ObjectProp();
            wrapper.setName("x");
            ArrayProp linesArray = new ArrayProp();
            linesArray.setName("lines");
            for (Entry entry : additionalXLines.entrySet()) {
                linesArray.addChild(
                        new ObjectProp(
                                new ValueProp("value", (Double) entry.getKey()),
                                new ValueProp("text", (String) entry.getValue(), true)));
            }
            wrapper.addChild(linesArray);
            addChild(wrapper);
        }
        if (additionalYLines != null) {
            ObjectProp wrapper = new ObjectProp();
            wrapper.setName("y");
            ArrayProp linesArray = new ArrayProp();
            linesArray.setName("lines");
            for (Entry entry : additionalYLines.entrySet()) {
                linesArray.addChild(
                        new ObjectProp(
                                new ValueProp("value", (Double) entry.getKey()),
                                new ValueProp("text", (String) entry.getValue(), true)));
            }
            wrapper.addChild(linesArray);
            addChild(wrapper);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Boolean getShowX() {
        return showX;
    }

    public void setShowX(Boolean showX) {
        this.showX = showX;
    }

    public Boolean getShowY() {
        return showY;
    }

    public void setShowY(Boolean showY) {
        this.showY = showY;
    }

    public void addXGrid(Double value, String label) {
//        xGridAdd.getAdditionalLines().put(value, label);
        additionalXLines.put(value, label);
    }

    public void addYGrid(Double value, String label) {
//        yGridAdd.getAdditionalLines().put(value, label);
        additionalYLines.put(value, label);
    }

    public void removeYGrids() {
        fire("yGridRemove", true);
        additionalYLines.clear();
    }

    public void removeXGrids() {
        fire("xGridRemove", true);
        additionalXLines.clear();
    }
}
