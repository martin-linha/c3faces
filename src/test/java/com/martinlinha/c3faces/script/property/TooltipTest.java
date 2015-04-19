package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TooltipTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Tooltip tooltip = new Tooltip();
        assertEquals("", tooltip.getScript());
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Tooltip tooltip = new Tooltip();
        tooltip.setGrouped(false);
        tooltip.setShow(true);

        tooltip.getScript();
        tooltip.getScript();

        assertEquals(StringUtils.deleteWhitespace("tooltip: {show: true, grouped: false}"), StringUtils.deleteWhitespace(tooltip.getScript()));
    }
}
