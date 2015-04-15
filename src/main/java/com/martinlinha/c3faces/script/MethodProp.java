package com.martinlinha.c3faces.script;

import com.martinlinha.c3faces.util.JSTools;
import java.util.Arrays;

/**
 *
 * @author Martin Linha
 */
public class MethodProp extends Property {

    private final String[] params;

    public MethodProp(String eventName, String body, String... params) {
        setName(eventName);
        setBody(body);
        this.params = params;
    }

    public MethodProp(String body, String... params) {
        setBody(body);
        this.params = params;
    }

    @Override
    public String getPrefix() {
        return "function (" + JSTools.commaSeparatedStrings(Arrays.asList(params)) + ") {";
    }

    @Override
    public String getSuffix() {
        return "}";
    }
}
