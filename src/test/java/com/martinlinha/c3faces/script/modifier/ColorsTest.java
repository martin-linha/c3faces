package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.model.C3DataSet;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.property.Data;
import java.util.Arrays;
import java.util.LinkedHashSet;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ColorsTest {

    /**
     * Test generated script with listened change.
     */
    @Test
    public void testGetScriptWithChange() {

        Colors colors = new Colors();
        Data data = new Data();
        C3ViewDataSet c3ViewDataSet = new C3ViewDataSet("Name 1", new C3DataSet(Arrays.asList(1, 2, 3, 4, 5)), "#EEAAEE");

        data.addListener(colors);
        data.setDataSets(new LinkedHashSet<>(Arrays.asList(c3ViewDataSet)));

        c3ViewDataSet.setColor("green");

        assertEquals(StringUtils.deleteWhitespace("chart.data.colors({" + c3ViewDataSet.getId() + ": 'green'});"), StringUtils.deleteWhitespace(colors.getScript("chart", 500)));
    }
}
