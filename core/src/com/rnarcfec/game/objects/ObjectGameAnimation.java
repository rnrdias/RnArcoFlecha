/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rnarcfec.game.util.BatchView;

/**
 *
 * @author RNR
 */
public class ObjectGameAnimation extends AbstractGameRectangle {

    private final Animation animation;
    private boolean playRevese;
    //private int frameCurrent;
    private boolean isPlay;
    private boolean isFrameStart;
    private boolean frameStartExecute;
    private float stateTimeStart;
    //private int frameGo;
    public float stateTime;
    public float stateTimeGo;
    private final float frameDuration;

    //private final AbstractGameRectangle gameRectangle;
    public ObjectGameAnimation(Animation animation, float frameDuration, float offsetX, float offsetY, float width, float height) {
        super(offsetX, offsetY, width, height);
        this.animation = animation;
        this.frameDuration = frameDuration;
        animation.setFrameDuration(frameDuration);
    }

    //MUDAR O METODO
    public void update(float deltaTime) {
        if (playRevese) {
            stateTime -= deltaTime;
            if (stateTime <= stateTimeGo) {
                isPlay = false;
                stateTime = stateTimeGo;
            } else {
                isPlay = true;
            }

            if (stateTime <= stateTimeStart && frameStartExecute) {
                isFrameStart = true;
                frameStartExecute = false;
            }
        } else {
            stateTime += deltaTime;
            if (stateTime >= stateTimeGo) {
                isPlay = false;
                stateTime = stateTimeGo;
            } else {
                isPlay = true;
            }
            if (stateTime >= stateTimeStart && frameStartExecute) {
                isFrameStart = true;
                frameStartExecute = false;
            }
        }
    }

    @Override
    public void render(BatchView batch) {
        TextureRegion reg = (TextureRegion) animation.getKeyFrame(stateTime % animation.getAnimationDuration(), true);
        batch.draw(reg, getX(), getY());
        super.render(batch);
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void inicializeFrameStart(int frame) {
        isFrameStart = false;
        frameStartExecute = true;

        stateTimeStart = frame * frameDuration + 1000 * animation.getAnimationDuration();
        if (playRevese && stateTime < stateTimeStart) {
            stateTimeStart -= animation.getAnimationDuration();
        } else if (stateTime > stateTimeStart) {
            stateTimeStart += animation.getAnimationDuration();
        }
    }

    public boolean isFrameStart() {
        return isFrameStart;
    }

    public void resetFrameStart() {
        isFrameStart = false;
    }

    public void repete() {
        if (isPlay) {
            return;
        }

        stateTimeGo = stateTime;
        if (playRevese) {
            stateTimeGo -= animation.getAnimationDuration();
        } else {
            stateTimeGo += animation.getAnimationDuration();
        }
    }

    public void setAnimationKeyFrame(int frameGo, boolean playRevese) {
        stateTime = (stateTime % animation.getAnimationDuration()) + 1000 * animation.getAnimationDuration();

        stateTimeGo = frameGo * frameDuration + 1000 * animation.getAnimationDuration();

        this.playRevese = playRevese;
        if (playRevese && stateTime < stateTimeGo) {
            stateTimeGo -= animation.getAnimationDuration();
        } else if (stateTime > stateTimeGo) {
            stateTimeGo += animation.getAnimationDuration();
        }
    }
}
