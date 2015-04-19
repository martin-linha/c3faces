package com.martinlinha.c3faces.script.property;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ZoomTest {

    /**
     * Testing right generated script, no script
     */
    @Test
    public void testNoScript() {
        Zoom zoom = new Zoom();
        assertEquals("", zoom.getScript());
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Zoom zoom = new Zoom();
        zoom.setEnabled(true);
        zoom.setExtentFrom(20);
        zoom.setExtentTo(100);
        zoom.setOnzoomMethodProp(new OnzoomMethod("console.log('onzoom test');", "param1", "param2"));
        zoom.setRescale(false);

        zoom.getScript();

        assertEquals(StringUtils.deleteWhitespace(
                "zoom: {enabled: true, rescale: false, extent: [20, 100], onzoom: function (param1, param2) {console.log('onzoom test');}}"),
                StringUtils.deleteWhitespace(zoom.getScript()));
    }
}
