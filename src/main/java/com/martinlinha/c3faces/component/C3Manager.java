package com.martinlinha.c3faces.component;

import com.martinlinha.c3faces.script.property.Data;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Martin Linha
 */
public class C3Manager implements Serializable {

    public static final String SESSION_KEY = "c3manager";
    private boolean dataChanged;

    private final Set<Data> dataProperties = new HashSet<>();

    public C3Manager() {
    }

    public void addData(String key, Data newVal) {
        boolean dataExists = false;
        for (Data data : dataProperties) {
            if (data == newVal) {
                dataExists = true;
            }
        }
        if (!dataExists) {
            dataProperties.add(newVal);
        }
        dataChanged = !dataExists;
    }

    public boolean isDataChanged() {
        return dataChanged;
    }
}
