package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.property.Legend;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class LegendHide extends Modifier {

        private static final String LEGEND = "legend";
    private static final String HIDE = "hide";
    
    @Override
    protected Property getModificationProperty() {
        return new ValueBlock();
    }

    @Override
    protected String getMethodName() {
        return HIDE;
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList(LEGEND);
    }

    @Override
    public boolean isMethod() {
        if (getPropertyLastChange(Legend.EVENT_LEGEND_HIDE) != null) {
            return (boolean) getPropertyLastChange(Legend.EVENT_LEGEND_HIDE);
        }
        return super.isMethod();
    }
}
