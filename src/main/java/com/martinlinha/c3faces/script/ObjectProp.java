package com.martinlinha.c3faces.script;

import com.martinlinha.c3faces.listener.ChangeListener;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class ObjectProp extends Property {

    private static final String PREFIX = "{";
    private static final String SUFFIX = "}";

    public ObjectProp() {
    }

    public ObjectProp(List<ChangeListener> listeners) {
        super(listeners);
    }

    public ObjectProp(String name, String body) {
        setName(name);
        setBody(body);
    }

    public ObjectProp(Property... properties) {
        addChildren(Arrays.asList(properties));
    }

    public ObjectProp(String name, Property... properties) {
        setName(name);
        addChildren(Arrays.asList(properties));
    }

    public ObjectProp(String body) {
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
