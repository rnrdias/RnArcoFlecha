/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.rnarcfec.game.Assets;
import com.rnarcfec.game.util.BatchView;

/**
 *
 * @author RNR
 */
public class ObjGrama {
    Texture grama;
    
    public ObjGrama(){
        grama = Assets.instance.grama.grama;
    }
    
    public void render(BatchView batch) {
        batch.draw(grama, 0, 0);
    }
    
    /*
    Texture grama;

    public ObjGrama() {
        grama = Assets.instance.grama.grama;

        stateTime = 0;
    }

    @Override
    public void update(float deltaTime) {
        //super.update(deltaTime);
    }

    public void render(BatchView batch) {
        //super.render(batch);
        //batch.draw(grama, 0, 0);

        batch.viewportExtendApply();
        batch.draw(grama, 0, 0);

        batch.viewportApply();

    }*/

}
