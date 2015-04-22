package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ArrayBlock;
import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;
import java.util.ArrayList;
import java.util.List;

/**
 * Through this class is possible to define chart's visual properties, specifically adds Regions to chart.
 *
 *
 * @see http://c3js.org/reference.html for attrs info
 * @author Martin Linha
 */
public class Regions extends ValueBlock {

    public static String NAME = "regions";

    private List<Region> regions = new ArrayList<>();

    public Regions() {
    }

    public Regions(List<Region> regions) {
        this.regions = regions;
    }

    @Override
    protected void preScriptBuild() {
        if (regions != null && !regions.isEmpty()) {
            ArrayBlock regionsArray = new ArrayBlock();
            for (Region region : regions) {
                ObjectBlock regionObj = new ObjectBlock();
                regionObj.addChild(new ValueBlock("axis", region.getAxis(), true));
                regionObj.addChild(new ValueBlock("start", region.getStart()));
                regionObj.addChild(new ValueBlock("end", region.getEnd()));
                regionObj.addChild(new ValueBlock("class", region.getCssClass(), true));
                regionsArray.addChild(regionObj);
            }
            addChild(regionsArray, true);
        }
    }

    public List<Region> getRegions() {
        return regions;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
