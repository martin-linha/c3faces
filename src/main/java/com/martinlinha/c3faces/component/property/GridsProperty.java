package com.martinlinha.c3faces.component.property;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.modifier.XGridAdd;
import com.martinlinha.c3faces.script.modifier.XGridRemove;
import com.martinlinha.c3faces.script.modifier.YGridAdd;
import com.martinlinha.c3faces.script.modifier.YGridRemove;
import com.martinlinha.c3faces.script.property.GridProperties;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;

/**
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.property.GridsProperty")
public class GridsProperty extends C3Property {
    
    private static final String ATTR_SHOW_X = "showX";
    private static final String ATTR_SHOW_Y = "showY";
    
    @Override
    public Property getAssociatedProperty() {
        GridProperties grid = new GridProperties();
        Map<Double, String> additionalXLines = null;
        Map<Double, String> additionalYLines = null;
        Modifier mod = new XGridAdd()
                .addModifier(new XGridRemove())
                .addModifier(new YGridAdd())
                .addModifier(new YGridRemove());
        
        for (UIComponent component : getChildren()) {
            if (component instanceof GridXProperty) {
                if (additionalXLines == null) {
                    additionalXLines = new HashMap<>();
                }
                additionalXLines.put(Double.parseDouble((String) component.getAttributes().get(GridProperty.ATTR_VALUE)),
                        (String) component.getAttributes().get(GridProperty.ATTR_TEXT));
                
            }
            if (component instanceof GridYProperty) {
                if (additionalYLines == null) {
                    additionalYLines = new HashMap<>();
                }
                additionalYLines.put(Double.parseDouble((String) component.getAttributes().get(GridProperty.ATTR_VALUE)),
                        (String) component.getAttributes().get(GridProperty.ATTR_TEXT));
            }
        }
        if (additionalXLines != null) {
            for (Entry<Double, String> entry : additionalXLines.entrySet()) {
                grid.addXGrid(entry.getKey(), entry.getValue());
            }
        }
        if (additionalYLines != null) {
            for (Entry<Double, String> entry : additionalYLines.entrySet()) {
                grid.addYGrid(entry.getKey(), entry.getValue());
            }
        }
        grid.addListener(mod);
        return grid;
    }
}
