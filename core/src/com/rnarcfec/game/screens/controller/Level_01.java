/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens.controller;

import com.badlogic.gdx.utils.Array;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.objects.ObjBexiga;
import com.rnarcfec.game.util.Constants;

/**
 *
 * @author RNR
 */
public class Level_01 extends ControlScreenGame {

    final int bexigaQuant = 10;
    final int bexigaDistanc = 60;
    final int bexigaPontos = 10;
    final int flechaInicQuant = 10;

    Array<ObjBexiga> bexigas;

    public Level_01(RnArcFecGame game, ControlScreenGameGUI controlScreenGameGUI) {
        super(game, controlScreenGameGUI);

        controlScreenGameGUI.addFlechas(flechaInicQuant);
        float posX = game.camera.viewportWidth;
        bexigas = new Array();

        for (int i = 0; i < bexigaQuant; i++) {
            ObjBexiga bexiga = new ObjBexiga();
            posX -= bexigaDistanc;
            bexiga.setX(posX);
            bexiga.setY((i % 2) * bexigaDistanc);
            bexigas.add(bexiga);
        }
    }

    void controlBexiga(float deltaTime) {
        for (ObjBexiga bexiga : bexigas) {
            if (!bexiga.remove) {
                bexiga.setY(Constants.BEXIGA_VELOC * deltaTime + bexiga.getY());
            } else {
                bexiga.setY(-Constants.BEXIGA_VELOC * deltaTime + bexiga.getY());
            }

            if (bexiga.getY() > game.camera.viewportHeight) {
                bexiga.setY(-10);
            }
        }
        for (ObjBexiga bexiga : bexigas) {

            if (overlaps(bexiga) && !bexiga.remove) {
                bexiga.remove = true;
                bexiga.setAnimationKeyFrame(ObjBexiga.KEY_FRAME_BEXIGA_ESTOURO, false);
                controlScreenGameGUI.addPontos(bexigaPontos);
            }
            if (bexiga.remove && bexiga.getY() < -10) {
                bexigas.removeValue(bexiga, true);
            }
        }

        /*if(bexigas.size == 0){
            
        }*/
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for (ObjBexiga bexiga : bexigas) {
            bexiga.update(deltaTime);
        }
        controlBexiga(deltaTime);
    }

    @Override
    public void render() {
        super.render();

        for (ObjBexiga bexiga : bexigas) {
            bexiga.render(game.batch);
        }

    }
}
