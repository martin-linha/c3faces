package com.martinlinha.c3faces.script.modifier;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class YGridAdd extends GridsMod {

    @Override
    protected String getMethodName() {
        return "add";
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList("ygrids");
    }
}
