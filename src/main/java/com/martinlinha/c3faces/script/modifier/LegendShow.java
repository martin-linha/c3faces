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
public class LegendShow extends Modifier {

    private boolean legendShow;

    @Override
    protected Property getModificationProperty() {
        return new ValueProp();
    }

    @Override
    protected String getMethodName() {
        return "show";
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList("legend");
    }

    @Override
    public boolean isMethod() {
        if (getPropertyLastChange("legendShow") != null) {
            return (boolean) getPropertyLastChange("legendShow");
        }
        return super.isMethod();
    }
}
