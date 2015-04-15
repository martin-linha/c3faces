package com.martinlinha.c3faces.listener;

/**
 *
 * @author Martin Linha
 */
public interface PropertyModifier extends ChangeListener {

    String getScript(String parent, int duration);

    int totalDuration();

    void reset();
}
