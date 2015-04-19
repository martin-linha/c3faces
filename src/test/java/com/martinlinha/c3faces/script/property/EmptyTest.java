package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EmptyTest {

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
}
