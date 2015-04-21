package com.martinlinha.c3faces.listener.change;

/**
 * Class used to easily instantiate cumulatible changes. (ie. without need to explicitly set the cumulatible attribute).
 *
 * It's mainly used to differentiate changes made on C3ViewDataSet objects.
 *
 * @author Martin Linha
 */
public class ViewDataSetCumulatibleChange extends Change {

    public ViewDataSetCumulatibleChange(String name, Object value) {
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
