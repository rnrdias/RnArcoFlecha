/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.objects;

import com.rnarcfec.game.Assets;
import com.rnarcfec.game.util.BatchView;
import com.rnarcfec.game.util.Constants;

/**
 *
 * @author RNR
 */
public class ObjBexiga extends ObjectGameAnimation {

    public static final int KEY_FRAME_BEXIGA_ESTOURO = 9;
    public boolean remove = false;
    

    public ObjBexiga() {
        super(Assets.instance.bexiga.animBexiga, Constants.ANIM_BEXIGA_FRAME_RATE, 19, 54, 27, 70);
        remove = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void render(BatchView batch) {
        super.render(batch);
    }

}
