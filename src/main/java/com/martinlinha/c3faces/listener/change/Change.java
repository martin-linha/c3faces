package com.martinlinha.c3faces.listener.change;

import java.util.HashSet;
import java.util.Set;

/**
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

    protected final void addValue(T value) {
        lastChangeValue = value;
        changeSet.add(value);
    }

    public boolean isCumulatible() {
        return cumulatible;
    }

    public Set<T> getChangeSet() {
        return changeSet;
    }

    public T getLastChange() {
        return lastChangeValue;
    }

    public String getName() {
        return name;
    }
}
