package com.martinlinha.c3faces.listener.change;

/**
 * Class used to differentiate changes made on C3ViewDataSet objects from other changes.
 *
 * @author Martin Linha
 */
public class ViewDataSetChange extends Change {

    public ViewDataSetChange(String name, Object value) {
        super(name, value);
    }
}
