package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;

/**
 *
 * @author Martin Linha
 */
public class GaugeProperties extends Pie {

    public static String NAME = "gauge";

    private Integer width;
    private Integer min;
    private Integer max;
    private String units;

    public GaugeProperties() {
    }

    public GaugeProperties(Integer width, Integer min, Integer max, String units, Boolean expand, Boolean labels) {
        super(expand, labels);
        this.width = width;
        this.min = min;
        this.max = max;
        this.units = units;
    }

    @Override
    protected void preScriptBuild() {
        addChild(new ValueProp("width", width));
        addChild(new ValueProp("min", min));
        addChild(new ValueProp("max", max));
        addChild(new ValueProp("units", units, true));
        addChild(new ValueProp("expand", super.getExpand()));
        addChild(new ObjectProp("label", new ValueProp("show", super.getShowLabels())));
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
