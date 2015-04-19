package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.property.Size;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Martin Linha
 */
public class ResizeTest {

    /**
     * Test generated script with listened change.
     */
    @Test
    public void testGetScriptWithChange() {
        Size size = new Size();
        Resize resize = new Resize();
        size.addListener(resize);

        size.setHeight(100);
        size.setHeight(200);
        
        resize.getScript("chart", 500);

        assertEquals(StringUtils.deleteWhitespace("setTimeout(function () {chart.resize({height: 200})}, 500); "), StringUtils.deleteWhitespace(resize.getScript("chart", 500)));
    }
}
