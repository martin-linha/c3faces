package com.martinlinha.c3faces.model;

import java.util.Collections;
import java.util.List;

/**
 * Class represents series of data units in chart. Once is created, list is unmodifiable. To modify data series, just create new instance of this
 * object.
 *
 * Note that C3.js supports only numeric values, so wrapped collection is of corresponding type.
 *
 * @author Martin Linha
 */
public class C3DataSet {

    private final List<? extends Number> values;

    public C3DataSet(List<? extends Number> values) {
        this.values = Collections.unmodifiableList(values);
    }

    /**
     * Return data series.
     *
     * @return Data series
     */
    public List<? extends Number> getValues() {
        return values;
    }
}
