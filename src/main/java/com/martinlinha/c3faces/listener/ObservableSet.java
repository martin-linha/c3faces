package com.martinlinha.c3faces.listener;

import com.martinlinha.c3faces.listener.change.CumulatibleChange;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Martin Linha
 * @param <T>
 */
public class ObservableSet<T> implements Set<T> {

    private final String eventAddedName;
    private final String eventRemovedName;
    private Set<T> wrappedSet;
    private List<ChangeListener> listeners = new LinkedList<>();

    public ObservableSet(Set<T> wrappedSet, String eventAddedName, String eventRemovedName) {
        this.wrappedSet = wrappedSet;
        this.eventAddedName = eventAddedName;
        this.eventRemovedName = eventRemovedName;
    }

    public List<ChangeListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<ChangeListener> listeners) {
        this.listeners = listeners;
    }

    public Set<T> getWrappedSet() {
        return wrappedSet;
    }

    public void setWrappedSet(Set<T> wrappedSet) {
        for (T o : wrappedSet) {
            fireAdded(o);
        }
        for (T o : this.wrappedSet) {
            fireRemoved(o);
        }
        this.wrappedSet = wrappedSet;
    }

    @Override
    public int size() {
        return wrappedSet.size();
    }

    @Override
    public boolean add(T e) {
        fireAdded(e);
        return wrappedSet.add(e);
    }

    @Override
    public boolean isEmpty() {
        return wrappedSet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return wrappedSet.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return wrappedSet.iterator();
    }

    @Override
    public Object[] toArray() {
        return wrappedSet.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return wrappedSet.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        fireRemoved((T) o);
        return wrappedSet.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return wrappedSet.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T o : c) {
            fireAdded(o);
        }
        return wrappedSet.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Set<T> changeSet = new HashSet<>();
        changeSet.addAll(wrappedSet);
        boolean changed = wrappedSet.removeAll(c);
        if (changed) {
            changeSet.removeAll(wrappedSet);
            for (T o : changeSet) {
                fireRemoved(o);
            }
        }

        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Set<T> changeSet = new HashSet<>();
        changeSet.addAll(wrappedSet);
        boolean changed = wrappedSet.retainAll(c);
        if (changed) {
            changeSet.removeAll(wrappedSet);
            for (T o : changeSet) {
                fireRemoved(o);
            }
        }

        return changed;
    }

    @Override
    public void clear() {
        for (T o : wrappedSet) {
            fireRemoved(o);
        }
        wrappedSet.clear();
    }

    private void fire(String name, Object value) {
        for (ChangeListener listener : listeners) {
            listener.onChange(new CumulatibleChange(name, value));
        }
    }

    private void fireAdded(T o) {
        fire(eventAddedName, o);
    }

    private void fireRemoved(T o) {
        fire(eventRemovedName, o);
    }
}
