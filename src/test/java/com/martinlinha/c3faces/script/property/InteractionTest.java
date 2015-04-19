package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class InteractionTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Interaction interaction = new Interaction();
        assertEquals("", interaction.getScript());
    }

    /**
     * Testing right generated script, interaction enabled
     */
    @Test
    public void testScriptEnabled() {
        Interaction interaction = new Interaction();
        interaction.setEnabled(true);
        assertEquals(StringUtils.deleteWhitespace("interaction: { enabled: true }"), StringUtils.deleteWhitespace(interaction.getScript()));
    }
}
