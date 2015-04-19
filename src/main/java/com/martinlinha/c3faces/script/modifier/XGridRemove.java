package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.Property;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class XGridRemove extends Modifier {

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
        return Arrays.asList("xgrids");
    }

    @Override
    public boolean isMethod() {
        if (getPropertyLastChange("xGridRemove") != null) {
            return (boolean) getPropertyLastChange("xGridRemove");
        }
        return false;
    }
}
