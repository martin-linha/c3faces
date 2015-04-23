package com.martinlinha.c3faces.script;

import com.martinlinha.c3faces.util.JSTools;
import java.util.Arrays;

/**
 * Through this class can be built script blocks similar to Javascript function blocks. Name corresponds to function name, body to body of that
 * function. Can also contain function parameters. OUTPUT EXAMPLE:
 *
 * NAME: function (PARAM1, PARAM2) { BODY }
 *
 * @author Martin Linha
 */
public class MethodBlock extends Property {

    private final String[] params;

    /**
     * Constructs new .js function with event name, body including .js script and parameters. Example of output:
     *
     * NAME: function (PARAM1, PARAM2) { BODY }
     *
     * @param eventName Name of function
     * @param body Body of array block
     * @param params Function paramaters
     */
    public MethodBlock(String eventName, String body, String... params) {
        setName(eventName);
        setBody(body);
        this.params = params;
    }

    /**
     * Constructs new .js function with body including .js script and parameters. Example of output:
     *
     * function (PARAM1, PARAM2) { BODY }
     *
     * @param body Body of array block
     * @param params Function paramaters
     */
    public MethodBlock(String body, String... params) {
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
