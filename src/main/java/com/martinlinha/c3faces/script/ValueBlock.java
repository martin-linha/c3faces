package com.martinlinha.c3faces.script;

import java.util.Arrays;

/**
 * Through this class can be built script blocks of value type.
 *
 * OUTPUT EXAMPLE:
 *
 * NAME: BODY
 *
 * @author Martin Linha
 */
public class ValueBlock extends Property {

    private static final String PREFIX = "";
    private static final String SUFFIX = "";
    private boolean bodyQuoted;
    private boolean nameQuoted;

    public ValueBlock() {
    }

    /**
     * Constructs new value block with specified NAME and BODY. Example of output:
     *
     * NAME: BODY
     *
     * @param name Name of value block
     * @param body Body of value block
     */
    public ValueBlock(String name, String body) {
        setName(name);
        setBody(body);
    }

    /**
     * Constructs new value block with specified NAME and BODY. Example of output:
     *
     * NAME: BODY
     *
     * @param name Name of value block
     * @param body Body of value block
     */
    public ValueBlock(String name, Integer body) {
        setName(name);
        if (body != null) {
            setBody(body.toString());
        }
    }

    /**
     * Constructs new value block with specified NAME and BODY. Example of output:
     *
     * NAME: BODY
     *
     * @param name Name of value block
     * @param body Body of value block
     */
    public ValueBlock(String name, Boolean body) {
        setName(name);
        if (body != null) {
            setBody(body.toString());
        }
    }

    /**
     * Constructs new value block with specified NAME and BODY. Example of output:
     *
     * NAME: BODY
     *
     * @param name Name of value block
     * @param body Body of value block
     */
    public ValueBlock(String name, Double body) {
        setName(name);
        if (body != null) {
            setBody(body.toString());
        }
    }

    /**
     * Constructs new value block with specified NAME and BODY. BODY can be quoted. Example of output:
     *
     * NAME: 'BODY'
     *
     * @param name Name of value block
     * @param body Body of value block
     * @param bodyQuoted True if body should be quoted
     */
    public ValueBlock(String name, String body, boolean bodyQuoted) {
        setName(name);
        setBody(body);
        this.bodyQuoted = bodyQuoted;
    }

    /**
     * Constructs new value block with specified NAME and BODY. Both can be quoted. Example of output:
     *
     * 'NAME': 'BODY'
     *
     * @param name Name of value block
     * @param body Body of value block
     * @param bodyQuoted True if body should be quoted
     * @param nameQuoted True if name should be quoted
     */
    public ValueBlock(String name, String body, boolean bodyQuoted, boolean nameQuoted) {
        setName(name);
        setBody(body);
        this.bodyQuoted = bodyQuoted;
        this.nameQuoted = nameQuoted;
    }

    /**
     * Constructs new value block with specified BODY. Example of output:
     *
     * BODY
     *
     * @param body Body of value block
     */
    public ValueBlock(String body) {
        setBody(body);
    }

    /**
     * Constructs new value block with specified BODY and adds specified elements to object's children collection. Example of output:
     *
     * NAME: PROPERTIES...
     *
     * @param name Name of value block
     * @param properties Properties to be added to object's children collection
     */
    public ValueBlock(String name, Property... properties) {
        setName(name);
        addChildren(Arrays.asList(properties));
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public String getSuffix() {
        return SUFFIX;
    }

    @Override
    public String getBody() {
        if (super.getBody() != null && !super.getBody().isEmpty() && isBodyQuoted()) {
            return "'" + super.getBody() + "'";
        }
        return super.getBody();
    }

    @Override
    public String getName() {
        if (super.getName() != null && !super.getName().isEmpty() && isNameQuoted()) {
            return "'" + super.getName() + "'";
        }
        return super.getName();
    }

    /**
     * True if body is quoted.
     *
     * @return True if body is quoted
     */
    public boolean isBodyQuoted() {
        return bodyQuoted;
    }

    /**
     * True if name is quoted.
     *
     * @return True if name is quoted
     */
    public boolean isNameQuoted() {
        return nameQuoted;
    }
}
