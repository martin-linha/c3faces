package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.model.C3DataSet;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import java.util.Arrays;
import java.util.LinkedHashSet;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DataTest {

    /**
     * Testing right generated script, no data supplied
     */
    @Test
    public void testScriptEmptyData() {
        Data data = new Data();
        assertEquals(StringUtils.deleteWhitespace("data: {columns: []}"), StringUtils.deleteWhitespace(data.getScript()));
    }

    /**
     * Testing right generated script, combination
     */
    @Test
    public void testScriptCombination() {
        Data data = new Data();
        C3ViewDataSet c3ViewDataSet = new C3ViewDataSet("Name 1", new C3DataSet(Arrays.asList(1, 2, 3, 4, 5)), "#EEAAEE");

        data.setChartType(ChartType.STEP.getName());
        data.setDataSets(new LinkedHashSet<>(Arrays.asList(c3ViewDataSet)));
        data.addChild(new Bindto("divId123"));

        data.getScript();
        data.getScript();

        assertEquals(
                StringUtils.deleteWhitespace("data: {bindto: 'divId123', columns: [['" + c3ViewDataSet.getId() + "', 1, 2, 3, 4, 5]], names: {" + c3ViewDataSet.getId() + ": 'Name 1'}, colors: {" + c3ViewDataSet.getId() + ": '#EEAAEE'}, types: {" + c3ViewDataSet.getId() + ": 'line'}, type: 'step', selection: {enabled: true, multiple: false}}"),
                StringUtils.deleteWhitespace(data.getScript()));
    }
}
