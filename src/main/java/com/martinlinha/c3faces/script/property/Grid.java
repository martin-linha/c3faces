package com.martinlinha.c3faces.script.property;

/**
 *
 * @author Martin Linha
 */
public class Grid {

    private final Double value;
    private final String text;

    public Grid(Double value, String text) {
        this.value = value;
        this.text = text;
    }

    public Double getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
