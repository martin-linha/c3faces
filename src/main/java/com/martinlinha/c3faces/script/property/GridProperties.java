package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ArrayBlock;
import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class GridProperties extends ObjectBlock {

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

        ObjectBlock xGrids = new ObjectBlock();
        xGrids.setName("x");
        ArrayBlock xLines = new ArrayBlock();
        xLines.setName("lines");
        for (Grid grid : additionalXLines) {
            xLines.addChild(new ObjectBlock(
                            new ValueBlock("value", grid.getValue()),
                            new ValueBlock("text", grid.getText(), true)));
        }
        xGrids.addChild(xLines);
        addChild(xGrids);

        ObjectBlock wrapper = new ObjectBlock();
        wrapper.setName("y");
        ArrayBlock linesArray = new ArrayBlock();
        linesArray.setName("lines");
        for (Grid grid : additionalYLines) {
            linesArray.addChild(new ObjectBlock(
                            new ValueBlock("value", grid.getValue()),
                            new ValueBlock("text", grid.getText(), true)));
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
