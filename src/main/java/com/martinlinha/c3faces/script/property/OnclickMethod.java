package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.MethodBlock;

/**
 *
 * @author Martin Linha
 */
public class OnclickMethod extends MethodBlock {

    public OnclickMethod(String body, String... params) {
        super(body, params);
    }

    @Override
    public String getName() {
        return "onclick";
    }
}
