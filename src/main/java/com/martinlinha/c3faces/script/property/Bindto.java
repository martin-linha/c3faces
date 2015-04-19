package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ValueBlock;

/**
 *
 * @author Martin Linha
 */
public class Bindto extends ValueBlock {

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
