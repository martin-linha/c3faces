package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GridPropertiesTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        GridProperties gridProperties = new GridProperties();
        assertEquals("", gridProperties.getScript());
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        GridProperties gridProperties = new GridProperties();

        gridProperties.addXGrid(1d, "test2");
        gridProperties.addXGrid(2d, "test3");

        gridProperties.addYGrid(10d, "test4");
        gridProperties.addYGrid(20d, "test5");

        assertEquals(StringUtils.deleteWhitespace("grid: {x: {lines: [{value: 1.0, text: 'test2'}, {value: 2.0, text: 'test3'}]}, y: {lines: [{value: 10.0, text: 'test4'}, {value: 20.0, text: 'test5'}]}}"), StringUtils.deleteWhitespace(gridProperties.getScript()));
    }

    /**
     * Testing right generated script, combination - multiple times generated script
     */
    @Test
    public void testScriptCombinationMultiple() {
        GridProperties gridProperties = new GridProperties();

        gridProperties.addXGrid(1d, "test2");
        gridProperties.addXGrid(2d, "test3");

        gridProperties.addYGrid(10d, "test4");
        gridProperties.addYGrid(20d, "test5");

        gridProperties.getScript();
        gridProperties.getScript();
        gridProperties.getScript();

        assertEquals(StringUtils.deleteWhitespace("grid: {x: {lines: [{value: 1.0, text: 'test2'}, {value: 2.0, text: 'test3'}]}, y: {lines: [{value: 10.0, text: 'test4'}, {value: 20.0, text: 'test5'}]}}"), StringUtils.deleteWhitespace(gridProperties.getScript()));
    }
}
