package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.script.CommaValueBlock;
import com.martinlinha.c3faces.script.Property;

/**
 *
 * @author Martin Linha
 */
public class TransformType extends Modifier {

    private final String id;
    private final ChartType chartType;

    public TransformType(String id, ChartType chartType) {
        this.id = id;
        this.chartType = chartType;
    }

    @Override
    protected Property getModificationProperty() {
        String type = chartType != null ? chartType.getName() : null;
        return new CommaValueBlock(type, id, true, true);
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
