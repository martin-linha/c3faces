package com.martinlinha.c3faces.constants;

/**
 * Contains enumeration of actual chart types provided by C3.js.
 *
 * @author Martin Linha
 */
public enum ChartType {

    LINE("line"), SPLINE("spline"), STEP("step"), AREA("area"), AREASPLINE("area-spline"),
    AREASTEP("area-step"), BAR("bar"), SCATTER("scatter"), PIE("pie"), DONUT("donut"), GAUGE("gauge");

    private String name;

    private ChartType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
