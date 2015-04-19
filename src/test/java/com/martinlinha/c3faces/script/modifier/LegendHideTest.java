package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.property.Legend;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LegendHideTest {

    /**
     * Test generated script with listened change.
     */
    @Test
    public void testGetScriptWithChange() {
        Legend legend = new Legend();
        LegendHide legendHide = new LegendHide();
        legend.addListener(legendHide);

        legend.setHide(true);

        assertEquals(StringUtils.deleteWhitespace("setTimeout(function() {chart.legend.hide()}, 500);"), StringUtils.deleteWhitespace(legendHide.getScript("chart", 500)));
    }
}
