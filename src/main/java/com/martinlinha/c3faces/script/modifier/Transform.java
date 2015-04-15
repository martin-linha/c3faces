package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.script.ValueProp;
import com.martinlinha.c3faces.script.Property;

/**
 *
 * @author Martin Linha
 */
public class Transform extends Modifier {

    @Override
    protected Property getModificationProperty() {
        String chartType;
        if (getPropertyLastChange("chartType") == null) {
            chartType = null;
        } else {
            chartType = ((ChartType) getPropertyLastChange("chartType")).getName();
        }

        return new ValueProp(null, chartType, true);
    }

    @Override
    protected String getMethodName() {
        return "transform";
    }

    @Override
    public boolean includePropName() {
        return true;
    }
}
