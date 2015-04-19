package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PointTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Point point = new Point();
        assertEquals("", point.getScript());
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Point point = new Point();
        point.setExpand(false);
        point.setExpandR(5d);
        point.setR(10d);
        point.setSelectR(3d);
        point.setShow(true);
        
        point.getScript();
        point.getScript();
        
        assertEquals(StringUtils.deleteWhitespace("point: {show: true, r: 10.0, focus: {expand: {r: 5.0}}, select: {r: 3.0}}"), StringUtils.deleteWhitespace(point.getScript()));
    }
}
