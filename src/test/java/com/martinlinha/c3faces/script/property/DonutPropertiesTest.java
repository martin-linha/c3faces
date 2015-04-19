package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DonutPropertiesTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        DonutProperties donutProperties = new DonutProperties();
        assertEquals("", donutProperties.getScript());
    }

    /**
     * Testing right generated script, width
     */
    @Test
    public void testScriptWidth() {
        DonutProperties donutProperties = new DonutProperties();
        donutProperties.setWidth(10);
        assertEquals(StringUtils.deleteWhitespace("donut: { width: 10 }"), StringUtils.deleteWhitespace(donutProperties.getScript()));
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        DonutProperties donutProperties = new DonutProperties();
        donutProperties.setExpand(true);
        donutProperties.setShowLabel(false);
        donutProperties.setTitle("Donut test title.");
        donutProperties.setWidth(500);
        assertEquals(StringUtils.deleteWhitespace("donut: {expand: true, width: 500, label: {show: false}, title: 'Donut test title.'}"), StringUtils.deleteWhitespace(donutProperties.getScript()));
    }
}
