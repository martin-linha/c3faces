package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.property.GridProperties;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Martin Linha
 */
public class YGridRemoveTest {

    /**
     * Test generated script with listened change.
     */
    @Test
    public void testGetScriptWithChange() {
        GridProperties gridProperties = new GridProperties();
        YGridRemove yGridRemove = new YGridRemove();
        gridProperties.addListener(yGridRemove);

        gridProperties.addYGrid(30.2, "text 2");
        gridProperties.addYGrid(50.1, "text 3");

        gridProperties.removeYGrids();

        yGridRemove.getScript("chart", 500);
        yGridRemove.getScript("chart", 500);

        assertEquals(StringUtils.deleteWhitespace("setTimeout(function () {chart.ygrids.remove()}, 500); "), StringUtils.deleteWhitespace(yGridRemove.getScript("chart", 500)));
    }
}
