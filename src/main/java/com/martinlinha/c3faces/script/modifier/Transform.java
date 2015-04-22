package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.property.Data;

/**
 * Instances of this class can be registered as a listeners to listen changes fired by corresponding objects.
 *
 * The main mission of this class is to provide C3.js scripts for dynamic chart type changes.
 *
 * @author Martin Linha
 */
public class Transform extends Modifier {

    private static final String TRANSFORM = "transform";

    @Override
    protected Property getModificationProperty() {
        String chartType;
        if (getPropertyLastChange(Data.EVENT_CHART_TYPE_CHANGED) == null) {
            chartType = null;
        } else {
            chartType = ((ChartType) getPropertyLastChange(Data.EVENT_CHART_TYPE_CHANGED)).getName();
        }

        return new ValueBlock(null, chartType, true);
    }

    @Override
    protected String getMethodName() {
        return TRANSFORM;
    }

    @Override
    public boolean includePropName() {
        return true;
    }
}
