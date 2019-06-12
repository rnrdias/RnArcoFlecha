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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.VisibleAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rnarcfec.game.Assets;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.screens.util.AnimationAction;
import com.rnarcfec.game.util.Constants;
import com.rnarcfec.game.util.Linguagem;

/**
 *
 * @author RNR
 */
public final class ScreenLevel implements Screen {

    final RnArcFecGame game;
    public static final String TAG = ScreenLevel.class.getName();

    ImageButton buttonVoltar;
    Image fundo;
    Table placas;

    private final Stage stage;

    public ScreenLevel(final RnArcFecGame game) {
        this.game = game;

        stage = new Stage(game.viewportExtend, game.batch);
        Gdx.input.setInputProcessor(stage);
        stage.setDebugAll(Constants.DEBUG_GRAFICOS);

        loadComponents();
        loadListener();
    }

    public void setScreenIntr() {
        game.setScreen(new ScreenIntroducao(game));
    }

    private void scrollPlacas(float delta) {
        placas.setY(delta + placas.getY());
        if (placas.getY() > game.batch.viewportExtendHeight / 2) {
            placas.setY(game.batch.viewportExtendHeight / 2);
        }
        Gdx.app.debug(TAG + " scrollPlacas ", "Height:" + placas.getHeight()
                + " x:" + placas.getX() + " y:" + placas.getY()
                + " delta:" + delta + " " + game.batch.viewportExtendHeight);
    }

    @Override
    public void render(float delta) {

        fundo.setPosition(game.batch.viewportExtendWidth - 3237, -490);

        buttonVoltar.setPosition(game.batch.viewportExtendWidth - buttonVoltar.getWidth() * buttonVoltar.getScaleX() / 2 - 90, -buttonVoltar.getHeight() * buttonVoltar.getScaleY() / 2 + 95);

        placas.setX(game.batch.viewportExtendWidth / 2);
        stage.act(Gdx.graphics.getDeltaTime());

        
        if (placas.getY() + (placas.getHeight() - game.batch.viewportExtendHeight) < 0) {
            placas.setY(-(placas.getHeight() - game.batch.viewportExtendHeight));
        }

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

    void loadComponents() {

        fundo = new Image(Assets.instance.telaFundo.telaFundo);
        fundo.setScale(2.7f);
        buttonVoltar = new ImageButton(new TextureRegionDrawable(Assets.instance.button.bv));

        stage.addActor(fundo);
        stage.addActor(buttonVoltar);

        BitmapFont font = Assets.instance.fonts.gameScreen;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = new Color(0xb27c73ff);
        //labelStyle.font.getData().setScale(1.5f);
        
        int Quant = 8;
        placas = new Table();
        placas.center().bottom();
        SequenceAction actionPlacas = new SequenceAction();

        for (int i = 0; i < Quant; i++) {
            Placa placa = new Placa(new TextureRegionDrawable(Assets.instance.placa.placa.getKeyFrame(0)),
                    Linguagem.NIVEL + (i + 1), labelStyle);
            placa.label.setVisible(false);
            placa.setIndex(i);

            VisibleAction actionText = Actions.visible(true);
            actionText.setActor(placa.label);
            AnimationAction actionPlaca = new AnimationAction();
            actionPlaca.inicialize(Assets.instance.placa.placa, Constants.ANIM_PLACA_FRAME_RATE, placa.getStyle().up);
            actionPlaca.setActor(placa);
            actionPlacas.addAction(actionPlaca);
            actionPlacas.addAction(actionText);

            placas.setHeight(placas.getHeight() + placa.getHeight());
            placas.setY(-placas.getHeight());
            placas.add(placa).row();

            placa.addCaptureListener(new ClickListener() {
                int x, y;

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    this.x = (int) x / 20;
                    this.y = (int) y / 20;
                    Gdx.app.debug(TAG, "D: x:" + this.x + " y:" + this.y);
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    int vx = (int) x / 20;
                    int vy = (int) y / 20;
                    Gdx.app.debug(TAG, "U: x:" + vx + " y:" + vy);
                    if (this.x == vx && this.y == vy) {
                        Placa placa = (Placa) event.getListenerActor();
                        Gdx.app.debug(TAG, "Dentro:" + placa.label.getText() + " index:" + placa.getIndex());

                        game.setScreen(new ScreenIntroducao(game));
                    }
                }
            });
        }

        stage.addActor(placas);
        stage.addAction(actionPlacas);
    }

    void loadListener() {

        placas.addListener(new ActorGestureListener() {
            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                scrollPlacas(deltaY);
            }
        });

        stage.addListener(new InputListener() {
            @Override
            public boolean scrolled(InputEvent event, float x, float y, int amount) {
                scrollPlacas(amount * 50);
                return true;
            }
        });

        buttonVoltar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.moveTo(placas.getX(), placas.getHeight() + game.batch.viewportExtendHeight + game.batch.viewportExtendHeight / 2, 1));
                sequenceAction.addAction(
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreenPrevious();
                            }
                        }));

                placas.addAction(sequenceAction);
            }
        });
    }

    public class Placa extends Button {

        private int index;
        public Label label;

        //labelStyle.font.getData().setScale(1.5f);
        public Placa(Drawable imageUp, CharSequence text, LabelStyle labelStyle) {
            super(imageUp);
            label = new Label(text, labelStyle);
            label.setY(label.getY() + 500);
            super.add(label).padTop(30);
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}
