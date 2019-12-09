/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

/**
 *
 * @author RNR
 */
abstract public class WindowListener implements EventListener {

    @Override
    public boolean handle(Event event) {
        if (!(event instanceof WindowListener.CloseEvent)) {
            return false;
        }
        if (((CloseEvent) event).success) {
            closeSuccess((WindowListener.CloseEvent) event, event.getTarget());
        } else {
            closeNoSuccess((WindowListener.CloseEvent) event, event.getTarget());
        }
        return false;
    }

    abstract public void closeSuccess(CloseEvent event, Actor actor);

    abstract public void closeNoSuccess(CloseEvent event, Actor actor);

    /**
     * Fired when something in an actor has changed. This is a generic event,
     * exactly what changed in an actor will vary.
     *
     * @author Nathan Sweet
     */
    static public class CloseEvent extends Event {

        private boolean success;

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }

}
