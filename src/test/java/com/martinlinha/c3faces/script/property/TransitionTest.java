package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TransitionTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Transition transition = new Transition();
        assertEquals("", transition.getScript());
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Transition transition = new Transition();
        transition.setDuration(50000);

        transition.getScript();

        assertEquals(StringUtils.deleteWhitespace("transition: {duration: 50000}"), StringUtils.deleteWhitespace(transition.getScript()));
    }
}
