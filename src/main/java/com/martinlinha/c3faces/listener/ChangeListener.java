package com.martinlinha.c3faces.listener;

import com.martinlinha.c3faces.listener.change.Change;

/**
 *
 * @author Martin Linha
 */
public interface ChangeListener {

    void onChange(Change<?> change);
}
