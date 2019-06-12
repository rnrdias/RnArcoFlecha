/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rnarcfec.game.Assets;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.util.Linguagem;

/**
 *
 * @author RNR
 */
public class ScreenGameGUI implements Screen {

    final RnArcFecGame game;
    BitmapFont fontDefault;

    public ScreenGameGUI(RnArcFecGame game) {
        this.game = game;
        fontDefault = Assets.instance.fonts.defaultNormal;
    }

    @Override
    public void render(float delta) {
        fontDefault.draw(game.batch, Linguagem.GAME_PONTOS, 0, game.camera.viewportHeight);
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
