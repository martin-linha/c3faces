package com.martinlinha.c3faces.script;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Martin Linha
 */
public class ArrayProp extends Property {

    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

    public ArrayProp() {
    }

    public ArrayProp(String name, String body) {
        setName(name);
        setBody(body);
    }

    public ArrayProp(String name, Property... properties) {
        setName(name);
        setChildren(new HashSet(Arrays.asList(properties)));
    }

    public ArrayProp(String body) {
        setBody(body);
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public String getSuffix() {
        return SUFFIX;
    }
}
