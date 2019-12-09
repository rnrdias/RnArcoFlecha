/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens;

import com.badlogic.gdx.Screen;
import com.rnarcfec.game.RnArcFecGame;

/**
 *
 * @author RNR
 */
public class ScreenGameOver implements Screen {

    RnArcFecGame game;

    public ScreenGameOver(RnArcFecGame game) {
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.fontDefault.draw(game.batch, "Game Over", game.camera.viewportWidth / 2, game.camera.viewportHeight / 2);
        game.batch.end();

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
