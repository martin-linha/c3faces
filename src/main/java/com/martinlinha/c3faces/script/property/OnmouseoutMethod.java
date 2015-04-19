package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.MethodBlock;

/**
 *
 * @author Martin Linha
 */
public class OnmouseoutMethod extends MethodBlock {

    public OnmouseoutMethod(String body, String... params) {
        super(body, params);
    }

    @Override
    public String getName() {
        return "onmouseout";
    }

}
