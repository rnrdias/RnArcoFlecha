/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.rnarcfec.game.Assets;
import com.rnarcfec.game.util.BatchView;
import com.rnarcfec.game.util.Constants;

/**
 *
 * @author RNR
 */
public class ObjArco {

    public static final int KEY_FRAME_ARCO_ARMADO = 10;
    public static final int KEY_FRAME_ARCO_FLECHA_AUSENTE = 19;
    //public static final int KEY_FRAME_ARCO_FLECHA_DISPARADA = 20;
    public static final int KEY_FRAME_ARCO_FLECHA_NOVA = 25;
    public static final int KEY_FRAME_ARCO_POSICAO_INICIAL = 0;

    public static final int KEY_FRAME_PERNAS_PASSO = 0;
    public static final int START_FRAME_PERNAS_PASSO_CIMA = 13;
    public static final int START_FRAME_PERNAS_PASSO_BAIXO = 22;

    //public float frameGo;
    //public boolean playRevese;
    public float stateTime2;
    public Animation animation2;

    public int aniArco;
    public int aniPes;
    //public boolean isRunAniPes;

    private final ObjectGameAnimation gameAnimationCorpo;
    private final ObjectGameAnimation gameAnimationPernas;

    public ObjArco() {
        gameAnimationCorpo = new ObjectGameAnimation(Assets.instance.arcoCorpo.animArcoCorpo, Constants.ANIM_ARCO_FRAME_RATE, 10, 8, 50, 190);
        gameAnimationPernas = new ObjectGameAnimation(Assets.instance.arcoPernas.animArcoPernas, Constants.ANIM_PERNAS_FRAME_RATE, 0, 0, 0, 0);
    }

    public void update(float deltaTime) {
        gameAnimationCorpo.update(deltaTime);
        gameAnimationPernas.update(deltaTime);
    }

    public void render(BatchView batch) {
        gameAnimationPernas.render(batch);
        gameAnimationCorpo.render(batch);
    }

    public void setAnimationKeyFrameCorpo(int frameGo, boolean playRevese) {
        System.out.print("frameGo:" + frameGo);
        System.out.println("playRevese:" + playRevese);
        gameAnimationCorpo.setAnimationKeyFrame(frameGo, playRevese);
    }

    public void setAnimationKeyFramePernas(int frameGo, boolean playRevese) {
        gameAnimationPernas.setAnimationKeyFrame(frameGo, playRevese);
        gameAnimationPernas.repete();
    }

    public void setAnimationStartFramePernas(int frameStart) {
        gameAnimationPernas.inicializeFrameStart(frameStart);
    }

    public boolean isAnimationStartFramePernas() {
        return gameAnimationPernas.isFrameStart();
    }

    public boolean isAnimationCorpoPlay() {
        return gameAnimationCorpo.isPlay();
    }

    public boolean isAnimationPernasPlay() {
        return gameAnimationPernas.isPlay();
    }

    public void setX(float x) {
        gameAnimationCorpo.setX(x);
        gameAnimationPernas.setX(x);
    }

    public void setY(float y) {
        gameAnimationCorpo.setY(y);
        gameAnimationPernas.setY(y);
    }

    public float getX() {
        return gameAnimationCorpo.getX();
    }

    public float getY() {
        return gameAnimationCorpo.getY();
    }

    public void resetFrameStartPernas() {
        gameAnimationPernas.resetFrameStart();
    }

    /*
    public ObjArco() {
        this.animation = Assets.instance.arcoCorpo.animArcoCorpo;
        this.animation2 = Assets.instance.arcoPernas.animArcoPernas;
        stateTime = 0;
        stateTime2 = 0;
        bounds.width = ((TextureAtlas.AtlasRegion) animation.getKeyFrame(0)).getRegionWidth();
        bounds.height = ((TextureAtlas.AtlasRegion) animation.getKeyFrame(0)).getRegionHeight();
        //offset.y = -bounds.height + 70;
       
    }

    @Override
    public void update(float deltaTime) {

        if (aniArco != 0) {
            stateTime += deltaTime;
            switch (aniArco) {
                case ARCO_ARMADO:
                    if (stateTime > Assets.ANIM_ARCO_FRAME_RATE * 10) {
                        stateTime = Assets.ANIM_ARCO_FRAME_RATE * 10;
                        aniArco = 0;
                    }
                    break;
                case ARCO_FLECHA_DISPARADA:
                    if (stateTime > Assets.ANIM_ARCO_FRAME_RATE * 20) {
                        stateTime = Assets.ANIM_ARCO_FRAME_RATE * 20;
                        aniArco = 0;
                    }
                    break;
                case ARCO_FLECHA_NOVA:
                    if (stateTime > Assets.ANIM_ARCO_FRAME_RATE * 26) {
                        stateTime = Assets.ANIM_ARCO_FRAME_RATE * 26;
                        aniArco = 0;
                    }
                    break;
                default:
                    if (stateTime > Assets.ANIM_ARCO_FRAME_RATE * 30) {
                        //stateTime = Assets.ANIM_ARCO_FRAME_RATE * 30;
                        stateTime = 0;
                        aniArco = 0;
                    }
                    break;
            }

        } else {
            //stateTime = 0;
        }

        if (aniPes != 0) {
            stateTime2 += deltaTime;

            if (aniPes == 1) {
                animation2.setPlayMode(Animation.PlayMode.NORMAL);
                if (stateTime2 > Assets.ANIM_ARCO_FRAME_RATE * 11) {
                    aniPes = 0;
                }
            } else {
                animation2.setPlayMode(Animation.PlayMode.REVERSED);
                if (stateTime2 > Assets.ANIM_ARCO_FRAME_RATE * 3) {
                    aniPes = 0;
                }
            }
            if (stateTime2 > Assets.ANIM_ARCO_FRAME_RATE * 23) {
                //stateTime2 = Assets.ANIM_ARCO_FRAME_RATE * 23;
                stateTime2 = 0;
            }
        } else {
            stateTime2 = 0;
        }

        //animation2.setPlayMode(Animation.PlayMode.REVERSED);
        position.x = bounds.getX();
        position.y = bounds.getY();
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = (TextureRegion) animation.getKeyFrame(stateTime, false);
        batch.draw(reg, position.x, position.y);

        TextureRegion reg2 = (TextureRegion) animation2.getKeyFrame(stateTime2, false);
        batch.draw(reg2, position.x, position.y);

        super.render(batch);
        //update(Gdx.graphics.getDeltaTime());
    }*/
}
