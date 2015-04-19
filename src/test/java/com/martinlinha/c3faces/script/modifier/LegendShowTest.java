package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.property.Legend;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LegendShowTest {

    /**
     * Test generated script with listened change.
     */
    @Test
    public void testGetScriptWithChange() {
        Legend legend = new Legend();
        LegendShow legendShow = new LegendShow();
        legend.addListener(legendShow);

        legend.setShow(true);

        assertEquals(StringUtils.deleteWhitespace("setTimeout(function() {chart.legend.show()}, 500);"), StringUtils.deleteWhitespace(legendShow.getScript("chart", 500)));
    }
}
