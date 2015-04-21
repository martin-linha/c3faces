package com.martinlinha.c3faces.listener;

/**
 * Interface which extends ChangeListener and is implemented by classed which mainly generates C3.js script.
 *
 * @author Martin Linha
 */
public interface PropertyModifier extends ChangeListener {

    /**
     * Returns generated script.
     *
     * @param parent Subject on which should be script executed (mainly .js name of chart's variable)
     * @param duration Duration of animation in millis
     * @return Generated script
     */
    String getScript(String parent, int duration);

    /**
     * Because of class can contains other animations, total duration can be different than script duration.
     *
     * @return Total duration in millis
     */
    int totalDuration();

    /**
     * Method mainly for resetting state of implementing objects.
     *
     */
    void reset();
}
