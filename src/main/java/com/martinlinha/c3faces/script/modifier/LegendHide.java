package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.ValueProp;
import com.martinlinha.c3faces.script.Property;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class LegendHide extends Modifier {

    @Override
    protected Property getModificationProperty() {
        return new ValueProp();
    }

    @Override
    protected String getMethodName() {
        return "hide";
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList("legend");
    }

    @Override
    public boolean isMethod() {
        if (getPropertyLastChange("legendHide") != null) {
            return (boolean) getPropertyLastChange("legendHide");
        }
        return super.isMethod();
    }
}
