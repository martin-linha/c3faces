package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AxisTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Axis axis = new Axis();
        assertEquals("", axis.getScript());
    }

    /**
     * Testing right generated script, no script after null setter
     */
    @Test
    public void testNoScriptNullSetter() {
        Axis axis = new Axis();
        axis.setRotated(true);
        axis.setRotated(null);
        assertEquals("", axis.getScript());
    }

    /**
     * Testing right generated script, rotated true
     */
    @Test
    public void testScriptRotatedTrue() {
        Axis axis = new Axis();
        axis.setRotated(true);
        assertEquals(StringUtils.deleteWhitespace("axis: { rotated: true }"), StringUtils.deleteWhitespace(axis.getScript()));
    }

    /**
     * Testing right generated script, show X true
     */
    @Test
    public void testScriptShowXTrue() {
        Axis axis = new Axis();
        axis.setShowX(true);
        assertEquals(StringUtils.deleteWhitespace("axis: { x: { show: true } }"), StringUtils.deleteWhitespace(axis.getScript()));
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Axis axis = new Axis();
        axis.setShowX(true);
        axis.setShowY(true);
        axis.setRotated(true);
        axis.setTypeX(Axis.Type.INDEXED);
        assertEquals(StringUtils.deleteWhitespace("axis:{[rotated:true,x:{show:true,type:indexed}],y:{show:true}}"), StringUtils.deleteWhitespace(axis.getScript()));
    }
}
