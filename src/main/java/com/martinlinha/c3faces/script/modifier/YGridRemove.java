package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class YGridRemove extends Modifier {

    @Override
    protected Property getModificationProperty() {
        return new ValueBlock();
    }

    @Override
    protected String getMethodName() {
        return "remove";
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList("ygrids");
    }

    @Override
    public boolean isMethod() {
        if (getPropertyLastChange("yGridRemove") != null) {
            return (boolean) getPropertyLastChange("yGridRemove");
        }
        return false;
    }
}
