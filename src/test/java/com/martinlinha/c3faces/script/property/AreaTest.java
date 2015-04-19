package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AreaTest {

    private final Area area;

    public AreaTest() {
        area = new Area();
    }

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        assertEquals("", area.getScript());
    }

    /**
     * Testing right generated script, zerobased false
     */
    @Test
    public void testZeroBasedFalse() {
        area.setZerobased(false);
        assertEquals(StringUtils.deleteWhitespace("area: { zerobased: false }"), StringUtils.deleteWhitespace(area.getScript()));
    }

    /**
     * Testing right generated script, zerobased true
     */
    @Test
    public void testZeroBasedTrue() {
        area.setZerobased(true);
        assertEquals(StringUtils.deleteWhitespace("area: { zerobased: true }"), StringUtils.deleteWhitespace(area.getScript()));
    }
}
