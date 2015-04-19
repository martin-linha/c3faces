package com.martinlinha.c3faces.script;

import java.util.Arrays;

/**
 *
 * @author Martin Linha
 */
public class ArrayBlock extends Property {
    
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";
    
    public ArrayBlock() {
    }
    
    public ArrayBlock(String name, String body) {
        setName(name);
        setBody(body);
    }
    
    public ArrayBlock(String name, Property... properties) {
        setName(name);
        addChildren(Arrays.asList(properties));
    }
    
    public ArrayBlock(String body) {
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
