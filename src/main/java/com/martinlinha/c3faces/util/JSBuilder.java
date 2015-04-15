package com.martinlinha.c3faces.util;

import com.martinlinha.c3faces.script.Property;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class JSBuilder {

    private StringBuilder builder = new StringBuilder();

    public JSBuilder() {
    }

    public static JSBuilder build() {
        return new JSBuilder();
    }

    public JSBuilder c3() {
        builder.append("c3");
        return this;
    }

    public JSBuilder var(String entity) {
        builder.append("var ").append(entity).append(" = ");
        return this;
    }

    public JSBuilder endLine() {
        builder.append("; \n");
        return this;
    }

    public String getResult() {
        String res = builder.toString();
        builder = new StringBuilder();
        return res;
    }

    public JSBuilder generate(Collection<Property> properties) {
        builder.append(".generate({");
        List<Property> attributes = new ArrayList<>(properties);
        for (int i = 0; i < attributes.size(); i++) {
            Property attr = attributes.get(i);
            String script = attr.getScript();
            if (i > 0 && !script.isEmpty()) {
                builder.append(", ");
            }
            builder.append(script);
        }

        builder.append("})");
        return this;
    }
}
