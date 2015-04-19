package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.property.GridProperties;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Martin Linha
 */
public class XGridRemoveTest {

    /**
     * Test generated script with listened change.
     */
    @Test
    public void testGetScriptWithChange() {
        GridProperties gridProperties = new GridProperties();
        XGridRemove xGridRemove = new XGridRemove();
        gridProperties.addListener(xGridRemove);

        gridProperties.addXGrid(30.2, "text 2");
        gridProperties.addXGrid(50.1, "text 3");

        gridProperties.removeXGrids();

        xGridRemove.getScript("chart", 500);
        xGridRemove.getScript("chart", 500);

        assertEquals(StringUtils.deleteWhitespace("setTimeout(function () {chart.xgrids.remove()}, 500); "), StringUtils.deleteWhitespace(xGridRemove.getScript("chart", 500)));
    }
}
