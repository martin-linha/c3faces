package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.MethodBlock;

/**
 *
 * @author Martin Linha
 */
public class OnzoomMethod extends MethodBlock {

    public OnzoomMethod(String body, String... params) {
        super(body, params);
    }

    @Override
    public String getName() {
        return "onzoom";
    }
}