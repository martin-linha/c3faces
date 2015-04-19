package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SizeTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Size size = new Size();
        assertEquals("", size.getScript());
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Size size = new Size();
        size.setWidth(10);
        size.setHeight(20);

        size.getScript();
        size.getScript();

        assertEquals(StringUtils.deleteWhitespace("size: {width: 10, height: 20}"), StringUtils.deleteWhitespace(size.getScript()));
    }
}
