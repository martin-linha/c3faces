package com.martinlinha.c3faces.listener.change;

import com.martinlinha.c3faces.listener.ChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Martin Linha
 */
public abstract class Changes implements ChangeListener {

    private final Map<String, Change> changedProps = new HashMap<>();

    public Object getPropertyLastChange(String propName) {
        if (changedProps.get(propName) == null) {
            return null;
        }
        return changedProps.get(propName).getLastChange();
    }

    public Set<ViewDataSetChange> getViewDataSetChanges() {
        Set<ViewDataSetChange> set = new HashSet<>();
        for (Entry entry : changedProps.entrySet()) {
            if (entry.getValue() instanceof ViewDataSetChange) {
                set.add((ViewDataSetChange) entry.getValue());
            }
        }
        return set;
    }

    public Set<?> getPropertyChangeSet(String propName) {
        if (changedProps.get(propName) == null) {
            return null;
        }
        return changedProps.get(propName).getChangeSet();
    }

    @Override
    public void onChange(Change<?> change) {
        Change changedProp = changedProps.get((String) change.getName());
        if (changedProp != null && changedProp.isCumulatible()) {
            changedProp.addValue(change.getLastChange());
        } else {
            changedProps.put(change.getName(), change);
        }
    }

    public Map<String, Change> getChangedProps() {
        return changedProps;
    }
}
