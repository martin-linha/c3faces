package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BindtoTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Bindto bindto = new Bindto("");
        assertEquals("", bindto.getScript());
    }

    /**
     * Testing right generated script, divID
     */
    @Test
    public void testScriptBody() {
        Bindto bindto = new Bindto("divID");
        
        bindto.getScript();
        
        assertEquals(StringUtils.deleteWhitespace("bindto: 'divID'"), StringUtils.deleteWhitespace(bindto.getScript()));
    }
}
