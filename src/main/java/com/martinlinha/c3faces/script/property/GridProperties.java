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

    private Map<Double, String> additionalXLines = new HashMap<>();
    private Map<Double, String> additionalYLines = new HashMap<>();

    public GridProperties() {
    }

    public GridProperties(Map<Double, String> additionalXLines, Map<Double, String> additionalYLines) {
        this.additionalXLines = additionalXLines;
        this.additionalYLines = additionalYLines;
    }

    @Override
    protected void preScriptBuild() {

        ObjectProp xGrids = new ObjectProp();
        xGrids.setName("x");
        ArrayProp xLines = new ArrayProp();
        xLines.setName("lines");
        for (Entry entry : additionalXLines.entrySet()) {
            xLines.addChild(
                    new ObjectProp(
                            new ValueProp("value", (Double) entry.getKey()),
                            new ValueProp("text", (String) entry.getValue(), true)));
        }
        xGrids.addChild(xLines);
        addChild(xGrids);

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

    @Override
    public String getName() {
        return NAME;
    }

    public void addXGrid(Double value, String label) {
        additionalXLines.put(value, label);
    }

    public void addYGrid(Double value, String label) {
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
