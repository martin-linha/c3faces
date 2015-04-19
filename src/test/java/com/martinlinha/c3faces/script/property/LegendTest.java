package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LegendTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Legend legend = new Legend();
        assertEquals("", legend.getScript());
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Legend legend = new Legend();
        legend.setShow(false);
        legend.setInsetAnchor(Legend.InsetAnchor.TOPLEFT);
        legend.setInsetX(5);
        legend.setItemOnclick(new OnclickMethod("testFunction", "a", "b"));
        legend.setItemOnmouseout(new OnmouseoutMethod("testFunctionOut", "c", "d", "e"));
        legend.setPosition(Legend.Position.INSET);

        legend.getScript();
        legend.getScript();

        assertEquals(StringUtils.deleteWhitespace("legend: {position: 'inset', show: false, inset: {anchor: 'top-left', x: 5}, item: {onclick: function (a, b) {testFunction}, onmouseout: function (c, d, e) {testFunctionOut}}}"), StringUtils.deleteWhitespace(legend.getScript()));
    }
}
