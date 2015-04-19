package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PieTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Pie pie = new Pie();
        assertEquals("", pie.getScript());
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Pie pie = new Pie();
        pie.setExpand(false);
        pie.setShowLabel(false);

        pie.getScript();
        pie.getScript();

        assertEquals(StringUtils.deleteWhitespace("pie: {expand: false, label: {show: false}}"), StringUtils.deleteWhitespace(pie.getScript()));
    }
}
