package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.MethodBlock;

/**
 * Objects represents Onselected .js event and javascript function to be evaluated when this .js event is fired. Can be used only at places which defines
 * C3.js.
 *
 * @author Martin Linha
 */
public class OnselectedMethod extends MethodBlock {

    public OnselectedMethod(String body, String... params) {
        super(body, params);
    }

    @Override
    public String getName() {
        return "onselected";
    }
}
