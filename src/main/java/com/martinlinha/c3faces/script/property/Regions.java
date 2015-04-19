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
public class Regions extends ValueProp {

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
            ArrayProp regionsArray = new ArrayProp();
            for (Region region : regions) {
                ObjectProp regionObj = new ObjectProp();
                regionObj.addChild(new ValueProp("axis", region.getAxis(), true));
                regionObj.addChild(new ValueProp("start", region.getStart()));
                regionObj.addChild(new ValueProp("end", region.getEnd()));
                regionObj.addChild(new ValueProp("class", region.getCssClass(), true));
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
