package com.martinlinha.c3faces.script.property;

/**
 * Through this class is possible to define chart's visual properties, specifically adds grid to chart.
 *
 *
 * @see http://c3js.org/reference.html for attrs info
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
