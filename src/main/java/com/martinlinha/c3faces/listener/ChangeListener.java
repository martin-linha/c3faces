package com.martinlinha.c3faces.listener;

import com.martinlinha.c3faces.listener.change.Change;

/**
 * Interface which is implemented by classes, which would like to be notified about changes.
 *
 * @author Martin Linha
 */
public interface ChangeListener {

    /**
     * Method to be called after notification.
     *
     * @param change Change object containing info about change
     */
    void onChange(Change<?> change);
}
