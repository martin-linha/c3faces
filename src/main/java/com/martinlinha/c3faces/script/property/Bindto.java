package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ValueProp;

/**
 *
 * @author Martin Linha
 */
public class Bindto extends ValueProp {

    public static String NAME = "bindto";

    public Bindto(String body) {
        super(body);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean isBodyQuoted() {
        return true;
    }
}
