package com.martinlinha.c3faces.listener.change;

import java.util.HashSet;
import java.util.Set;

/**
 * Objects of this class containes changes made on chart's visual properties.
 *
 * @author Martin Linha
 * @param <T>
 */
public class Change<T> {

    private final String name;
    private T lastChangeValue;
    private final Set<T> changeSet = new HashSet<>();
    private final boolean cumulatible = false;

    public Change(String name, T value) {
        this.name = name;
        lastChangeValue = value;
        addValue(value);
    }

    /**
     * Add new change value. Sets last change and adds new change to change set.
     *
     * @param value Value of change
     */
    protected final void addValue(T value) {
        lastChangeValue = value;
        changeSet.add(value);
    }

    /**
     * If change is cumulatible, then saves all historical changes.
     *
     * @return True if is cumulatible
     */
    public boolean isCumulatible() {
        return cumulatible;
    }

    /**
     * Returns set of changes.
     *
     * @return All change objects.
     */
    public Set<T> getChangeSet() {
        return changeSet;
    }

    /**
     * Return last change added to instance of this class.
     *
     * @return Last change object
     */
    public T getLastChange() {
        return lastChangeValue;
    }

    /**
     * Returns name of change
     *
     * @return Name of change
     */
    public String getName() {
        return name;
    }
}
