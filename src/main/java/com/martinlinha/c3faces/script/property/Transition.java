package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;

/**
 * Through this class is possible to specify the duration of transition animations.
 *
 * NOTE: Mainly used for internal reasons. The duration is setted by C3Chart, so all C3Charts subtypes inherits it.
 *
 * @author Martin Linha
 */
public class Transition extends ObjectBlock {

    public static String NAME = "transition";

    private Integer duration;

    public Transition() {
    }

    public Transition(Integer duration) {
        this.duration = duration;
    }

    @Override
    protected void preScriptBuild() {
        addChild(new ValueBlock("duration", duration));
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
