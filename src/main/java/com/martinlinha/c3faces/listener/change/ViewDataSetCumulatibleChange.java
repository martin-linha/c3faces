package com.martinlinha.c3faces.listener.change;

/**
 *
 * @author Martin Linha
 */
public class ViewDataSetCumulatibleChange extends Change {

    public ViewDataSetCumulatibleChange(String name, Object value) {
        super(name, value);
    }

    @Override
    public boolean isCumulatible() {
        return true;
    }
}
