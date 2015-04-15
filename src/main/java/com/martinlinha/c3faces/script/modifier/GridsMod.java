package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;
import com.martinlinha.c3faces.script.Property;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Martin Linha
 */
public abstract class GridsMod extends Modifier {

    private Map<Double, String> additionalLines = new HashMap<>();

    @Override
    protected Property getModificationProperty() {
        ObjectProp obj = new ObjectProp();
        for (Entry<Double, String> entry : additionalLines.entrySet()) {
            obj.addChild(new ValueProp("value", entry.getKey()));
            obj.addChild(new ValueProp("text", entry.getValue(), true));
        }
        return obj;
    }

    @Override
    protected abstract String getMethodName();

    public Map<Double, String> getAdditionalLines() {
        return additionalLines;
    }
}
