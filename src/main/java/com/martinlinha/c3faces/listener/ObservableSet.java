package com.martinlinha.c3faces.listener;

import com.martinlinha.c3faces.listener.change.CumulatibleChange;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Implementation of Set class to provide observable activities. It wraps basic set of objects and before performing actions like add(), addAll(),
 * remove() fires an event to notify the registered listeners.
 *
 * @author Martin Linha
 * @param <T>
 */
public class ObservableSet<T> implements Set<T> {

    private final String eventAddedName;
    private final String eventRemovedName;
    private LinkedHashSet<T> wrappedSet;
    private List<ChangeListener> listeners = new LinkedList<>();

    public ObservableSet(LinkedHashSet<T> wrappedSet, String eventAddedName, String eventRemovedName) {
        this.wrappedSet = wrappedSet;
        this.eventAddedName = eventAddedName;
        this.eventRemovedName = eventRemovedName;
    }

    /**
     * Get associated listeners.
     *
     * @return All listeners
     */
    public List<ChangeListener> getListeners() {
        return listeners;
    }

    /**
     * Set registered listeners.
     *
     * @param listeners ChangeListeners to start listening
     */
    public void setListeners(List<ChangeListener> listeners) {
        this.listeners = listeners;
    }

    /**
     * Returns wrapped set
     *
     * @return Wrapped set
     */
    public Set<T> getWrappedSet() {
        return wrappedSet;
    }

    /**
     * Sets the wrapped set and fires corresponding events.
     *
     * @param wrappedSet
     */
    public void setWrappedSet(LinkedHashSet<T> wrappedSet) {
        for (T o : wrappedSet) {
            fireAdded(o);
        }
        for (T o : this.wrappedSet) {
            fireRemoved(o);
        }
        this.wrappedSet = wrappedSet;
    }

    /**
     * Returning size of wrapped set.
     *
     * @return Size of wrapped set
     */
    @Override
    public int size() {
        return wrappedSet.size();
    }

    /**
     * Adds new element to collection and fires corresponding event.
     *
     * @return True if successful
     */
    @Override
    public boolean add(T e) {
        fireAdded(e);
        return wrappedSet.add(e);
    }

    /**
     * Returns true if wrapped set is empty.
     *
     * @return True if wrapped set is empty
     */
    @Override
    public boolean isEmpty() {
        return wrappedSet.isEmpty();
    }

    /**
     * Returns true if wrapped set contains specified object
     *
     * @param o Object to be checked
     * @return True if wrapped set contains specified object
     */
    @Override
    public boolean contains(Object o) {
        return wrappedSet.contains(o);
    }

    /**
     * Returns Iterator of wrapped set.
     *
     * @return Iterator of wrapped set
     */
    @Override
    public Iterator<T> iterator() {
        return wrappedSet.iterator();
    }

    /**
     * Returns wrapped set as array of objects.
     *
     * @return Wrapped set as array of objects
     */
    @Override
    public Object[] toArray() {
        return wrappedSet.toArray();
    }

    /**
     * Returns wrapped set as array of objects of specified type.
     *
     * @param <T> Type of objects
     * @return Wrapped set as array of objects of specified type
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return wrappedSet.toArray(a);
    }

    /**
     * Remove specified object from set. Fires corresponding event.
     *
     * @param o Object to be removed
     * @return True if removal was successful
     */
    @Override
    public boolean remove(Object o) {
        fireRemoved((T) o);
        return wrappedSet.remove(o);
    }

    /**
     * Returns true if collection contains all specified objects.
     *
     * @param c Collection of objects to be checked
     * @return True if collection contains all objects
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return wrappedSet.containsAll(c);
    }

    /**
     * Add all objects of specified collection. Fires corresponding events.
     *
     * @param c Collection of objects to be added
     * @return True if addition was successful
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T o : c) {
            fireAdded(o);
        }
        return wrappedSet.addAll(c);
    }

    /**
     * Remove all objects of specified collection. Fires corresponding events.
     *
     * @param c Collection of objects to be removed
     * @return True if removal was successful
     */
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

    /**
     * Retain all of objects included in specified collection. Fires corresponding events.
     *
     * @param c Collection of retaining objects
     * @return True if any changes was made
     */
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

    /**
     * Clear all objects in set. Fires corresponding events.
     *
     */
    @Override
    public void clear() {
        for (T o : wrappedSet) {
            fireRemoved(o);
        }
        wrappedSet.clear();
    }

    /**
     * Notify listeners with fired Change object.
     *
     * @param name Name of change
     * @param value Value of change
     */
    private void fire(String name, Object value) {
        for (ChangeListener listener : listeners) {
            listener.onChange(new CumulatibleChange(name, value));
        }
    }

    /**
     * Fire added event.
     *
     * @param o Value of change
     */
    private void fireAdded(T o) {
        fire(eventAddedName, o);
    }

    /**
     * Fire removed event.
     *
     * @param o Value of change
     */
    private void fireRemoved(T o) {
        fire(eventRemovedName, o);
    }
}
