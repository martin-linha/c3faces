package com.martinlinha.c3faces.script;

import com.martinlinha.c3faces.listener.ChangeListener;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class ObjectBlock extends Property {

    private static final String PREFIX = "{";
    private static final String SUFFIX = "}";

    public ObjectBlock() {
    }

    public ObjectBlock(List<ChangeListener> listeners) {
        super(listeners);
    }

    public ObjectBlock(String name, String body) {
        setName(name);
        setBody(body);
    }

    public ObjectBlock(Property... properties) {
        addChildren(Arrays.asList(properties));
    }

    public ObjectBlock(String name, Property... properties) {
        setName(name);
        addChildren(Arrays.asList(properties));
    }

    public ObjectBlock(String body) {
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
