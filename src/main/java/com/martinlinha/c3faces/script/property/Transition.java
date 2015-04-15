package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ObjectProp;
import com.martinlinha.c3faces.script.ValueProp;

/**
 *
 * @author Martin Linha
 */
public class Transition extends ObjectProp {

    public static String NAME = "transition";

    private Integer duration;

    public Transition() {
    }

    public Transition(Integer duration) {
        this.duration = duration;
    }

    @Override
    protected void preScriptBuild() {
        if (duration != null) {
            addChild(new ValueProp("duration", duration));
        }
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
