package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.property.GridProperties;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Martin Linha
 */
public class YGridAddTest {

    /**
     * Test generated script with listened change.
     */
    @Test
    public void testGetScriptWithChange() {
        GridProperties gridProperties = new GridProperties();
        Modifier yGrid = new YGridAdd().addModifier(new YGridRemove());

        gridProperties.addListener(yGrid);
        gridProperties.addYGrid(1d, "test1");
        gridProperties.addYGrid(2d, "test2");
        gridProperties.addYGrid(3d, "test3");
        gridProperties.addYGrid(4d, "test4");

        yGrid.getScript("chart", 500);
        yGrid.getScript("chart", 500);

        assertTrue(StringUtils.deleteWhitespace(yGrid.getScript("chart", 500)).contains(StringUtils.deleteWhitespace("{value: 1.0, text: 'test1'}")));
        assertTrue(StringUtils.deleteWhitespace(yGrid.getScript("chart", 500)).contains(StringUtils.deleteWhitespace("{value: 3.0, text: 'test3'}")));
        assertTrue(StringUtils.deleteWhitespace(yGrid.getScript("chart", 500)).contains(StringUtils.deleteWhitespace("{value: 4.0, text: 'test4'}")));
        assertTrue(StringUtils.deleteWhitespace(yGrid.getScript("chart", 500)).contains(StringUtils.deleteWhitespace("{value: 2.0, text: 'test2'}")));
    }

}
