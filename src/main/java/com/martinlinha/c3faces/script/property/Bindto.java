package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ValueBlock;

/**
 * Through this class is possible to define div to which chart will be appended.
 * 
 * NOTE: mainly used for internal reasons of C3Faces (C3Chart components used their unique div id)
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
