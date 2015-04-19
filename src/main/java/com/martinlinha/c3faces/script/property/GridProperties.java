package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ArrayProp;
import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class GridProperties extends ObjectProp {

    public static String NAME = "grid";

    private List<Grid> additionalXLines = new ArrayList<>();
    private List<Grid> additionalYLines = new ArrayList<>();

    public GridProperties() {
    }

    public GridProperties(List<Grid> additionalXLines, List<Grid> additionalYLines) {
        this.additionalXLines = additionalXLines;
        this.additionalYLines = additionalYLines;
    }

    @Override
    protected void preScriptBuild() {

        ObjectProp xGrids = new ObjectProp();
        xGrids.setName("x");
        ArrayProp xLines = new ArrayProp();
        xLines.setName("lines");
        for (Grid grid : additionalXLines) {
            xLines.addChild(
                    new ObjectProp(
                            new ValueProp("value", grid.getValue()),
                            new ValueProp("text", grid.getText(), true)));
        }
        xGrids.addChild(xLines);
        addChild(xGrids);

        ObjectProp wrapper = new ObjectProp();
        wrapper.setName("y");
        ArrayProp linesArray = new ArrayProp();
        linesArray.setName("lines");
        for (Grid grid : additionalYLines) {
            linesArray.addChild(
                    new ObjectProp(
                            new ValueProp("value", grid.getValue()),
                            new ValueProp("text", grid.getText(), true)));
        }
        wrapper.addChild(linesArray);
        addChild(wrapper);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public void addXGrid(Double value, String label) {
        Grid grid = new Grid(value, label);
        fireCumulatible("xGridAdd", grid);
        additionalXLines.add(grid);
    }

    public void addYGrid(Double value, String label) {
        Grid grid = new Grid(value, label);
        fireCumulatible("yGridAdd", grid);
        additionalYLines.add(grid);
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
