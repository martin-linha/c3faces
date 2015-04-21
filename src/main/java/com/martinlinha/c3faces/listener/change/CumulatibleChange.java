package com.martinlinha.c3faces.listener.change;

/**
 * Class used to easily instantiate cumulatible changes. (ie. without need to explicitly set the cumulatible attribute).
 *
 * @author Martin Linha
 * @param <T>
 */
public class CumulatibleChange<T> extends Change {

    public CumulatibleChange(String name, Object value) {
        super(name, value);
    }

    /**
     * Override to set objects of this class to be cumulatible.
     *
     * @return true
     */
    @Override
    public boolean isCumulatible() {
        return true;
    }
}
