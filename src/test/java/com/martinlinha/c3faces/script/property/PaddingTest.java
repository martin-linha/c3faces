package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PaddingTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Padding padding = new Padding();
        assertEquals("", padding.getScript());
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Padding padding = new Padding();
        padding.setBottom(10);
        padding.setLeft(20);
        padding.setRight(30);
        padding.setTop(40);

        padding.getScript();
        padding.getScript();

        assertEquals(StringUtils.deleteWhitespace("padding: {top: 40, right: 30, left: 20, bottom: 10}"), StringUtils.deleteWhitespace(padding.getScript()));
    }
}
