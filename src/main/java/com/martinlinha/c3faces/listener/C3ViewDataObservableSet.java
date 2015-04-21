package com.martinlinha.c3faces.listener;

import com.martinlinha.c3faces.model.C3ViewDataSet;
import java.util.Collection;
import java.util.Set;

/**
 * Concrete implementation of ObservableSet to observe changes made on set of objects of type C3ViewDataSet.
 *
 * It also populates listeners for C3ViewDataSet.
 *
 * @author Martin Linha
 */
public class C3ViewDataObservableSet extends ObservableSet<C3ViewDataSet> {

    public C3ViewDataObservableSet(Set<C3ViewDataSet> wrappedSet, String eventAddedName, String eventRemovedName) {
        super(wrappedSet, eventAddedName, eventRemovedName);
    }

    /**
     * Set new wrapped set and populate listeners to all of its elements.
     *
     * @param wrappedSet To be set and populated
     */
    @Override
    public void setWrappedSet(Set<C3ViewDataSet> wrappedSet) {
        for (C3ViewDataSet c : wrappedSet) {
            c.setListeners(getListeners());
        }
        super.setWrappedSet(wrappedSet);
    }

    /**
     * Add new C3ViewDataSet and populate its listeners.
     *
     * @param e C3ViewDataSet to be added and populated
     * @return True if addition was successful
     */
    @Override
    public boolean add(C3ViewDataSet e) {
        e.setListeners(getListeners());
        return super.add(e);
    }

    /**
     * Add multiple new C3ViewDataSet and populate their listeners.
     *
     * @param c C3ViewDataSets to be added and populated
     * @return True if addition was successful
     */
    @Override
    public boolean addAll(Collection<? extends C3ViewDataSet> c) {
        for (C3ViewDataSet data : c) {
            data.setListeners(getListeners());
        }
        return super.addAll(c);
    }
}
