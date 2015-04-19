package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.model.C3DataSet;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.property.Data;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NamesTest {

    /**
     * Test generated script with listened change.
     */
    @Test
    public void testGetScriptWithChange() {

        Names names = new Names();
        Data data = new Data();
        C3ViewDataSet c3ViewDataSet = new C3ViewDataSet("Name 1", new C3DataSet(Arrays.asList(1, 2, 3, 4, 5)), "#EEAAEE");

        data.addListener(names);
        data.setDataSets(new HashSet<>(Arrays.asList(c3ViewDataSet)));

        c3ViewDataSet.setName("new name");

        assertEquals(StringUtils.deleteWhitespace("chart.data.names({" + c3ViewDataSet.getId() + ": 'new name'});"), StringUtils.deleteWhitespace(names.getScript("chart", 500)));
    }
}
