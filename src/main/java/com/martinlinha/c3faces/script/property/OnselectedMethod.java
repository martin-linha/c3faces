package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.MethodProp;

/**
 *
 * @author Martin Linha
 */
public class OnselectedMethod extends MethodProp {

    public OnselectedMethod(String body, String... params) {
        super(body, params);
    }

    @Override
    public String getName() {
        return "onselected";
    }
}
