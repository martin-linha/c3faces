package com.martinlinha.c3faces.component;

import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.script.Property;
import java.util.List;
import javax.faces.component.FacesComponent;

/**
 * Class for component which allows to declare C3.js chart of type Pie in facelet.
 *
 * It does not return any default properties to component.
 *
 * @author Martin Linha
 */
@FacesComponent("com.martinlinha.c3faces.component.Pie")
public class Pie extends C3Chart {

    @Override
    public List<Property> getDefaultProperties() {
        return null;
    }

    @Override
    public String getChartType() {
        return ChartType.PIE.getName();
    }
}
