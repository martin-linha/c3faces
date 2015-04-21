package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.property.Region;
import com.martinlinha.c3faces.script.property.Regions;
import com.martinlinha.c3faces.util.ComponentUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;

/**
 * This class allows to declare visual property of type com.martinlinha.c3faces.script.property.Regions in facelet.
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.RegionsProperty")
public class RegionsProperty extends C3Property {

    @Override
    public Property getAssociatedProperty() {
        Regions regions = new Regions();
        List<Region> regionList = null;
        for (UIComponent component : getChildren()) {
            if (component instanceof RegionProperty) {
                if (regionList == null) {
                    regionList = new ArrayList<>();
                }
                Region region = new Region((String) component.getAttributes().get(RegionProperty.ATTR_AXIS),
                        ComponentUtil.parseDouble(component.getAttributes().get(RegionProperty.ATTR_START)),
                        ComponentUtil.parseDouble(component.getAttributes().get(RegionProperty.ATTR_END)),
                        (String) component.getAttributes().get(RegionProperty.ATTR_CSS_CLASS));
                regionList.add(region);
            }
        }
        if (regionList != null) {
            regions.getRegions().addAll(regionList);
        }
        return regions;
    }
}
