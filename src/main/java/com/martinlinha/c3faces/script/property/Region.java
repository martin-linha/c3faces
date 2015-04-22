package com.martinlinha.c3faces.script.property;

/**
 * Through this class is possible to define chart's visual properties, specifically adds Region to chart.
 *
 *
 * @see http://c3js.org/reference.html for attrs info
 * @author Martin Linha
 */
public class Region {

    private String axis;
    private Double start;
    private Double end;
    private String cssClass;

    public Region(String axis, Double start, Double end, String cssClass) {
        this.axis = axis;
        this.start = start;
        this.end = end;
        this.cssClass = cssClass;
    }

    public String getAxis() {
        return axis;
    }

    public void setAxis(String axis) {
        this.axis = axis;
    }

    public Double getStart() {
        return start;
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public Double getEnd() {
        return end;
    }

    public void setEnd(Double end) {
        this.end = end;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

}
