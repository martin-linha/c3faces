package com.martinlinha.c3faces.script;

import com.martinlinha.c3faces.listener.ChangeListener;
import java.util.Arrays;
import java.util.List;

/**
 * Through this class can be built script blocks of object type.
 *
 * OUTPUT EXAMPLE:
 *
 * NAME: { BODY }
 *
 * @author Martin Linha
 */
public class ObjectBlock extends Property {

    private static final String PREFIX = "{";
    private static final String SUFFIX = "}";

    public ObjectBlock() {
    }

    /**
     * Constructs new empty object block and assigns specified listeners.
     *
     * @param listeners Listener list to be set
     */
    public ObjectBlock(List<ChangeListener> listeners) {
        super(listeners);
    }

    /**
     * Constructs new object block with specified NAME and BODY. Example of output:
     *
     * NAME: { BODY }
     *
     * @param name Name of object block
     * @param body Body of object block
     */
    public ObjectBlock(String name, String body) {
        setName(name);
        setBody(body);
    }

    /**
     * Constructs new empty object block and adds specified elements to object's children collection.
     *
     *
     * @param properties Properties to be added to object's children collection
     */
    public ObjectBlock(Property... properties) {
        addChildren(Arrays.asList(properties));
    }

    /**
     * Constructs new object block with specified NAME and adds specified elements to object's children collection. Example of output:
     *
     * NAME: { PROPERTIES... }
     *
     * @param name Name of object block
     * @param properties Properties to be added to object's children collection
     *
     */
    public ObjectBlock(String name, Property... properties) {
        setName(name);
        addChildren(Arrays.asList(properties));
    }

    /**
     * Constructs new object block with specified BODY. Example of output:
     *
     * { BODY }
     *
     * @param body Body of object block
     */
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
