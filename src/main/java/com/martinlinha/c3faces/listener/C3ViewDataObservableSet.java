package com.martinlinha.c3faces.listener;

import com.martinlinha.c3faces.model.C3ViewDataSet;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Martin Linha
 */
public class C3ViewDataObservableSet extends ObservableSet<C3ViewDataSet> {

    public C3ViewDataObservableSet(Set<C3ViewDataSet> wrappedSet, String eventAddedName, String eventRemovedName) {
        super(wrappedSet, eventAddedName, eventRemovedName);
    }

    @Override
    public boolean add(C3ViewDataSet e) {
        e.setListeners(getListeners());
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends C3ViewDataSet> c) {
        for (C3ViewDataSet data : c) {
            data.setListeners(getListeners());
        }
        return super.addAll(c);
    }
}
