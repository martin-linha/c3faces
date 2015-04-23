package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.CommaValueBlock;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;

/**
 * Instances of this class can be registered as a listeners to listen changes fired by corresponding objects.
 *
 * The main mission of this class is to provide C3.js scripts for dynamic chart type changes.
 *
 * @author Martin Linha
 */
public class TransformType extends Modifier {

    private static final String TRANSFORM = "transform";

    private final String id;
    private final String chartType;

    public TransformType(String id, String chartType) {
        this.id = id;
        this.chartType = chartType;
    }

    @Override
    protected Property getModificationProperty() {
        return new CommaValueBlock(chartType, id, true, true);
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
