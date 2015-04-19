package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BarPropertiesTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        BarProperties barProperties = new BarProperties();
        assertEquals("", barProperties.getScript());
    }

    /**
     * Testing right generated script, width
     */
    @Test
    public void testScriptWidth() {
        BarProperties barProperties = new BarProperties();
        barProperties.setWidth(10);
        assertEquals(StringUtils.deleteWhitespace("bar: { width: 10 }"), StringUtils.deleteWhitespace(barProperties.getScript()));
    }

    /**
     * Testing right generated script, zerobased true
     */
    @Test
    public void testScriptZerobased() {
        BarProperties barProperties = new BarProperties();
        barProperties.setZerobased(true);
        assertEquals(StringUtils.deleteWhitespace("bar: { zerobased: true }"), StringUtils.deleteWhitespace(barProperties.getScript()));
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        BarProperties barProperties = new BarProperties();
        barProperties.setZerobased(true);
        barProperties.setWidth(10);
        assertEquals(StringUtils.deleteWhitespace("bar: { width: 10, zerobased: true  }"), StringUtils.deleteWhitespace(barProperties.getScript()));
    }
}
