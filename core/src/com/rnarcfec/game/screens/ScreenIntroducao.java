/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.VisibleAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rnarcfec.game.Assets;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.util.Constants;

/**
 *
 * @author RNR
 */
public final class ScreenIntroducao implements Screen {

    final RnArcFecGame game;
    private final Stage stage;

    Image fundo;
    ImageButton buttonVoltar;
    ImageButton placaB;
    Label text1;
    Label text2;
    Label text3;

    public ScreenIntroducao(final RnArcFecGame game) {
        this.game = game;
        stage = new Stage(game.viewportExtend, game.batch);
        Gdx.input.setInputProcessor(stage);
        stage.setDebugAll(Constants.DEBUG_GRAFICOS);

        loadComponents();
        loadListener();
        animationInicialize();

    }

    @Override
    public void render(float delta) {
        text1.setPosition(game.batch.viewportExtendWidth / 2 - text1.getWidth() / 2, 380);
        text2.setPosition(game.batch.viewportExtendWidth / 2 - text2.getWidth() / 2, text2.getHeight() + text1.getY());
        text3.setPosition(game.batch.viewportExtendWidth / 2 - text3.getWidth() / 2, text2.getHeight() + text3.getHeight() + text1.getY());
        placaB.setPosition(game.batch.viewportExtendWidth / 2 - placaB.getWidth() / 2, game.batch.viewportExtendHeight - placaB.getHeight() + text1.getY());

        if (placaB.getY() > 0) {
            placaB.setY(0);
        }
        buttonVoltar.setPosition(game.batch.viewportExtendWidth - buttonVoltar.getWidth() / 2 - 90, 0);

        stage.act(Gdx.graphics.getDeltaTime());
        game.batch.viewportExtendApply();
        stage.draw();
        game.batch.viewportApply();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void show() {
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

    void loadComponents() {
        fundo = new Image(Assets.instance.telaFundo.telaFundo);
        fundo.setScale(2.7f);
        fundo.setPosition(game.batch.viewportExtendWidth - 3237, -490);
        buttonVoltar = new ImageButton(new TextureRegionDrawable(Assets.instance.button.bvB));
        buttonVoltar.getColor().a = 0;
        placaB = new ImageButton(new TextureRegionDrawable(Assets.instance.placa.placaB));
        placaB.getColor().a = 0;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont font = Assets.instance.fonts.gameScreen;
        labelStyle.font = font;
        labelStyle.fontColor = new Color(0xb27c73ff);
        //labelStyle.font.getData().setScale(1.5f);
        text3 = new Label("Há uma poluição de balões no aeroporto,", labelStyle);
        text3.setVisible(false);
        text2 = new Label("que prejudicar todo o tráfico aéreo,", labelStyle);
        text2.setVisible(false);
        text1 = new Label("e preciso resolver este problema.", labelStyle);
        text1.setVisible(false);

        stage.addActor(fundo);
        stage.addActor(placaB);
        stage.addActor(buttonVoltar);
        stage.addActor(text1);
        stage.addActor(text2);
        stage.addActor(text3);

    }

    void loadListener() {

        buttonVoltar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                animationFinalize();
            }
        });

        placaB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ScreenGame(game));
            }
        });
    }
    
    void animationInicialize() {
        MoveToAction actionMoveFundo = Actions.moveTo(game.batch.viewportExtendWidth - 2432, -962, 1);
        ScaleToAction actionScaleFundo = Actions.scaleTo(4, 4, 1);
        ParallelAction actionPFundo = new ParallelAction(actionMoveFundo, actionScaleFundo);
        ScaleToAction actionScaleFundo2 = Actions.scaleTo(3.9f, 3.9f, 0.5f);
        AlphaAction actionVPlacaB = Actions.fadeIn(0.1f);
        actionVPlacaB.setTarget(placaB);
        AlphaAction actionVButtonVoltar = Actions.fadeIn(0.1f);
        actionVButtonVoltar.setTarget(buttonVoltar);
        ParallelAction actionPButtons = new ParallelAction(actionVPlacaB, actionVButtonVoltar);
        VisibleAction text1Action = Actions.visible(true);
        text1Action.setTarget(text1);
        VisibleAction text2Action = Actions.visible(true);
        text2Action.setTarget(text2);
        VisibleAction text3Action = Actions.visible(true);
        text3Action.setTarget(text3);
        SequenceAction actionSFundo = new SequenceAction(actionPFundo, actionScaleFundo2, actionPButtons);
        actionSFundo.addAction(text1Action);
        actionSFundo.addAction(text2Action);
        actionSFundo.addAction(text3Action);
        fundo.addAction(actionSFundo);
    }

    void animationFinalize() {
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        AlphaAction actionVPlacaB = Actions.fadeOut(0.1f);
        actionVPlacaB.setTarget(placaB);
        AlphaAction actionVButtonVoltar = Actions.fadeOut(0.1f);
        actionVButtonVoltar.setTarget(buttonVoltar);
        ScaleToAction actionScaleFundo2 = Actions.scaleTo(4f, 4f, 0.5f);
        ParallelAction actionPButtons = new ParallelAction(actionVPlacaB, actionVButtonVoltar, actionScaleFundo2);
        MoveToAction actionMoveFundo = Actions.moveTo(game.batch.viewportExtendWidth - 3237, -490, 1);
        ScaleToAction actionScaleFundo = Actions.scaleTo(2.7f, 2.7f, 1);
        ParallelAction actionPFundo = new ParallelAction(actionMoveFundo, actionScaleFundo);

        SequenceAction actionSFundo = new SequenceAction(actionPButtons, actionScaleFundo2, actionPFundo);
        actionSFundo.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreenPrevious();
            }
        }));
        fundo.addAction(actionSFundo);
    }

}
