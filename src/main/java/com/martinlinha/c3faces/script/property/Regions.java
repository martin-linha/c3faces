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
            ArrayProp array = new ArrayProp();
            for (Region region : regions) {
                ObjectProp obj = new ObjectProp();
                if (region.getAxis() != null) {
                    obj.addChild(new ValueProp("axis", region.getAxis(), true));
                }
                if (region.getStart() != null) {
                    obj.addChild(new ValueProp("start", region.getStart()));
                }
                if (region.getEnd() != null) {
                    obj.addChild(new ValueProp("end", region.getEnd()));
                }
                if (region.getCssClass() != null) {
                    obj.addChild(new ValueProp("class", region.getCssClass(), true));
                }
                array.addChild(obj);
            }
            addChild(array);
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
