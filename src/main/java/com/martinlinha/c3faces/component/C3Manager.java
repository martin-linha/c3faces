package com.martinlinha.c3faces.component;

import com.martinlinha.c3faces.script.property.Data;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Class which is used to handle instances of type com.martinlinha.c3faces.script.property.Data for chart objects. When chart's class is executed,
 * asks C3Manager if Data instance remains the same from the last request about Data object. Because C3Manager is of session scope, it knows the
 * instances for required period. This allows chart handles some difficult situations.
 *
 * @author Martin Linha
 */
public class C3Manager implements Serializable {

    public static final String SESSION_KEY = "c3manager";
    private boolean dataChanged;

    private final Set<Data> dataProperties = new HashSet<>();

    /**
     * Adds Data instance to session scoped map, where key is chart's component clientId and value instance of
     * com.martinlinha.c3faces.script.property.Data class.
     *
     * @param key Chart's clientId
     * @param newVal Assigned Data object to chart
     */
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

    /**
     * Returns true if Data instance have changed after new addition.
     *
     * @return True if Data instance changed
     */
    public boolean isDataChanged() {
        return dataChanged;
    }
}
