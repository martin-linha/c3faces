package com.martinlinha.c3faces.listener.change;

/**
 *
 * @author Martin Linha
 * @param <T>
 */
public class CumulatibleChange<T> extends Change {

    public CumulatibleChange(String name, Object value) {
        super(name, value);
    }

    @Override
    public boolean isCumulatible() {
        return true;
    }
}
