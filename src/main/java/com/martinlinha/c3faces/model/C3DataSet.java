package com.martinlinha.c3faces.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Linha
 */
public class C3DataSet {

    private List<Integer> values = new ArrayList();

    public C3DataSet(List<Integer> values) {
        this.values = values;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
