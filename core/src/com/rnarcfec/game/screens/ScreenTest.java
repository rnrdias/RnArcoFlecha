/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.screens.sub.SubScreenConfig;
import com.rnarcfec.game.util.Constants;

/**
 *
 * @author RNR
 */
public class ScreenTest implements Screen {

    final RnArcFecGame game;
    private final Stage stage;

    public ScreenTest(final RnArcFecGame game) {
        this.game = game;
        stage = new Stage(game.viewportExtend, game.batch);
        Gdx.input.setInputProcessor(stage);
        stage.setDebugAll(Constants.DEBUG_GRAFICOS);
        
        //My souce
        SubScreenConfig subScreenConfig = new SubScreenConfig(game);
        
        //stage.addActor(subScreenConfig);
        
    }

    @Override
    public void render(float delta) {
        stage.act(Gdx.graphics.getDeltaTime());

        game.batch.viewportExtendApply();
        stage.draw();
        game.batch.viewportApply();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
