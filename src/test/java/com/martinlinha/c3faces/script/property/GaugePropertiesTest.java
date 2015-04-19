package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GaugePropertiesTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        GaugeProperties gaugeProperties = new GaugeProperties();
        assertEquals("", gaugeProperties.getScript());
    }

    /**
     * Testing right generated script, width
     */
    @Test
    public void testScriptWidth() {
        GaugeProperties gaugeProperties = new GaugeProperties();
        gaugeProperties.setWidth(10);
        assertEquals(StringUtils.deleteWhitespace("gauge: { width: 10 }"), StringUtils.deleteWhitespace(gaugeProperties.getScript()));
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        GaugeProperties gaugeProperties = new GaugeProperties();
        gaugeProperties.setWidth(10);
        gaugeProperties.setExpand(false);
        gaugeProperties.setMax(100);
        gaugeProperties.setMin(300);
        gaugeProperties.setUnits("USD");

        gaugeProperties.getScript();
        gaugeProperties.getScript();

        assertEquals(StringUtils.deleteWhitespace("gauge: { width: 10, min: 300, max: 100, units: 'USD', expand: false }"), StringUtils.deleteWhitespace(gaugeProperties.getScript()));
    }
}
