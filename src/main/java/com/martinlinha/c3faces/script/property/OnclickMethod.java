package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.MethodProp;

/**
 *
 * @author Martin Linha
 */
public class OnclickMethod extends MethodProp {

    public OnclickMethod(String body, String... params) {
        super(body, params);
    }

    @Override
    public String getName() {
        return "onclick";
    }
}
