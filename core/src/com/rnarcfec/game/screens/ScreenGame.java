/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens;

import com.badlogic.gdx.Screen;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.screens.controller.ControlScreenGame;
import com.rnarcfec.game.screens.controller.ControlScreenGameGUI;
import com.rnarcfec.game.screens.controller.Level_01;
import com.rnarcfec.game.util.Constants;

/**
 *
 * @author RNR
 */
public class ScreenGame implements Screen {

    final RnArcFecGame game;

    ControlScreenGame controlScreenGame;
    ControlScreenGameGUI controlScreenGameGUI;

    public ScreenGame(RnArcFecGame game) {
        this.game = game;

        controlScreenGameGUI = new ControlScreenGameGUI(game, 0, Constants.START_LIVES, 0);
        controlScreenGame = new Level_01(game, controlScreenGameGUI);
    }

    @Override
    public void render(float delta) {

        controlScreenGame.update(delta);
        //screenGameGUI.update(delta);

        game.batch.begin();

        controlScreenGame.render();
        controlScreenGameGUI.render();

        game.batch.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
