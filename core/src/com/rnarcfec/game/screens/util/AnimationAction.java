/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens.util;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author RNR
 */
public class AnimationAction extends TemporalAction {

    private Animation animation;
    //private float frameDuration;
    private TextureRegionDrawable textureRegionDrawable;

    public void inicialize(Animation animation, float frameDuration, Drawable textureRegionDrawable) {
        this.animation = animation;
        //this.frameDuration = frameDuration;
        this.textureRegionDrawable = (TextureRegionDrawable) textureRegionDrawable;
        animation.setFrameDuration(frameDuration);
        setDuration(animation.getAnimationDuration());
    }

    /*protected void begin() {
        
    }*/
    @Override
    protected void update(float percent) {
        // percent range 0 - 1 float
        percent = percent * animation.getAnimationDuration();
        //Gdx.app.debug("upade", "t: " + percent);
        textureRegionDrawable.setRegion((TextureRegion) animation.getKeyFrame(percent));
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
        //this.frameDuration = frameDuration;
        //this.textureRegionDrawable = (TextureRegionDrawable) textureRegionDrawable;
        //animation.setFrameDuration(frameDuration);
        setDuration(animation.getAnimationDuration());
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setFrameDuration(float frameDuration) {
        //this.frameDuration = frameDuration;
        animation.setFrameDuration(frameDuration);
        setDuration(animation.getAnimationDuration());
    }

    public float getFrameDuration() {
        return animation.getFrameDuration();
    }

}
