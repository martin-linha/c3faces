package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SubchartTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Subchart subchart = new Subchart();
        assertEquals("", subchart.getScript());
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Subchart subchart = new Subchart();
        subchart.setShow(true);
        subchart.setOnbrushMethodProp(new OnbrushMethod("console.log('test');", "param1", "param2"));

        subchart.getScript();
        subchart.getScript();

        assertEquals(StringUtils.deleteWhitespace("subchart: {show: true, onbrush: function (param1, param2) {console.log('test');}}"), StringUtils.deleteWhitespace(subchart.getScript()));
    }
}
