package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.MethodProp;

/**
 *
 * @author Martin Linha
 */
public class OnunselectedMethod extends MethodProp {

    public OnunselectedMethod(String body, String... params) {
        super(body, params);
    }

    @Override
    public String getName() {
        return "onunselected";
    }
}
