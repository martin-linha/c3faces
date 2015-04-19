package com.martinlinha.c3faces.script.property;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RegionsTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Regions regions = new Regions();
        assertEquals("", regions.getScript());
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Regions regions = new Regions(Arrays.asList(
                new Region("x", 1d, 2d, "cssTest1"),
                new Region("x", 1.5d, 2.5d, "cssTest2"),
                new Region("y", 2d, 3d, "cssTest3")
        ));
        assertEquals(StringUtils.deleteWhitespace("regions: [{axis: 'x', start: 1.0, end: 2.0, class: 'cssTest1'}, {axis: 'x', start: 1.5, end: 2.5, class: 'cssTest2'},{axis: 'y', start: 2.0, end: 3.0, class: 'cssTest3'}]"), StringUtils.deleteWhitespace(regions.getScript()));
    }

    /**
     * Testing right generated script, combination with multiple generated script
     */
    @Test
    public void testScriptCombinationMultiple() {
        Regions regions = new Regions(Arrays.asList(
                new Region("x", 1d, 2d, "cssTest1"),
                new Region("x", 1.5d, 2.5d, "cssTest2"),
                new Region("y", 2d, 3d, "cssTest3")
        ));

        regions.getScript();
        regions.getScript();
        regions.getScript();
        regions.getScript();

        assertEquals(StringUtils.deleteWhitespace("regions: [{axis: 'x', start: 1.0, end: 2.0, class: 'cssTest1'}, {axis: 'x', start: 1.5, end: 2.5, class: 'cssTest2'},{axis: 'y', start: 2.0, end: 3.0, class: 'cssTest3'}]"), StringUtils.deleteWhitespace(regions.getScript()));
    }
}
