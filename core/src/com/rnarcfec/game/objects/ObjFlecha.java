/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.objects;

import com.rnarcfec.game.Assets;
import com.rnarcfec.game.util.BatchView;

/**
 *
 * @author RNR
 */
public class ObjFlecha extends AbstractGameObject {
    //AtlasRegion flecha;

    public ObjFlecha() {
        super(Assets.instance.flecha.flecha, 136, 186, 4, 4);
        
    }

    /*@Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }*/

    @Override
    public void render(BatchView batch) {
        super.render(batch);
    }

}
