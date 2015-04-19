package com.martinlinha.c3faces.script;

import java.util.Arrays;

/**
 *
 * @author Martin Linha
 */
public class ValueProp extends Property {

    private static final String PREFIX = "";
    private static final String SUFFIX = "";
    private boolean bodyQuoted;
    private boolean nameQuoted;

    public ValueProp() {
    }

    public ValueProp(String name, String body) {
        setName(name);
        setBody(body);
    }

    public ValueProp(String name, Integer val) {
        setName(name);
        if (val != null) {
            setBody(val.toString());
        }
    }

    public ValueProp(String name, Boolean val) {
        setName(name);
        if (val != null) {
            setBody(val.toString());
        }
    }

    public ValueProp(String name, Double val) {
        setName(name);
        if (val != null) {
            setBody(val.toString());
        }
    }

    public ValueProp(String name, String body, boolean bodyQuoted) {
        setName(name);
        setBody(body);
        this.bodyQuoted = bodyQuoted;
    }

    public ValueProp(String name, String body, boolean bodyQuoted, boolean nameQuoted) {
        setName(name);
        setBody(body);
        this.bodyQuoted = bodyQuoted;
        this.nameQuoted = nameQuoted;
    }

    public ValueProp(String body) {
        setBody(body);
    }

    public ValueProp(String name, Property... properties) {
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

    public boolean isBodyQuoted() {
        return bodyQuoted;
    }

    public boolean isNameQuoted() {
        return nameQuoted;
    }

    public void setBodyQuoted(boolean bodyQuoted) {
        this.bodyQuoted = bodyQuoted;
    }
}
