package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.property.GridProperties;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Martin Linha
 */
public class XGridAddTest {

    /**
     * Test generated script with listened change.
     */
    @Test
    public void testGetScriptWithChange() {
        GridProperties gridProperties = new GridProperties();
        XGridAdd xGridAdd = new XGridAdd();
        gridProperties.addListener(xGridAdd);

        gridProperties.addXGrid(2d, "text 1");
        gridProperties.addXGrid(30.2, "text 2");
        gridProperties.addXGrid(50.1, "text 3");

        xGridAdd.getScript("chart", 500);
        xGridAdd.getScript("chart", 500);

        assertTrue(StringUtils.deleteWhitespace(xGridAdd.getScript("chart", 500)).contains(StringUtils.deleteWhitespace("{value: 2.0, text: 'text 1'}")));
        assertTrue(StringUtils.deleteWhitespace(xGridAdd.getScript("chart", 500)).contains(StringUtils.deleteWhitespace("{value: 50.1, text: 'text 3'}")));
        assertTrue(StringUtils.deleteWhitespace(xGridAdd.getScript("chart", 500)).contains(StringUtils.deleteWhitespace("{value: 30.2, text: 'text 2'}")));

    }
}
