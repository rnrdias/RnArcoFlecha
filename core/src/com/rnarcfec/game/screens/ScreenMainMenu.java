/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens;

/**
 *
 * @author RNR
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rnarcfec.game.Assets;
import com.rnarcfec.game.GamePreferences;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.screens.sub.SubScreenConfig;
import com.rnarcfec.game.screens.util.WindowListener;
import com.rnarcfec.game.util.Constants;

/**
 *
 * @author RNR
 */
public final class ScreenMainMenu implements Screen {

    private final String TAG = ScreenMainMenu.class.getName();
    private final Stage stage;
    final RnArcFecGame game;
    BitmapFont font;

    ImageButton buttonConfig;
    ImageButton buttonAudio;
    ImageButton buttonPlay;

    Image pers;
    Image fundo;
    Image arvEsq;
    Image arvDir;
    Image titulo;

    static boolean inicializado = false;

    public ScreenMainMenu(final RnArcFecGame game) {
        this.game = game;
        stage = new Stage(game.viewportExtend, game.batch);

        Gdx.input.setInputProcessor(stage);
        stage.setDebugAll(Constants.DEBUG_GRAFICOS);

        loadComponents();
        loadListener();

        if (inicializado) {
            animationInicialize();
        } else {
            inicializado = true;
        }

    }

    @Override
    public void render(float delta) {
        arvEsq.setPosition(0, 0);
        arvDir.setPosition(game.batch.viewportExtendWidth - arvDir.getImageWidth(), 0);
        titulo.setPosition(50, game.batch.viewportExtendHeight - titulo.getHeight() - 50);
        pers.setPosition(0, 0);
        buttonPlay.setPosition(game.batch.viewportExtendWidth - buttonPlay.getWidth() / 2 - 90, 25);
        buttonAudio.setPosition(game.batch.viewportExtendWidth - buttonAudio.getWidth() / 2 - 90, buttonPlay.getHeight() + 50);
        buttonConfig.setPosition(game.batch.viewportExtendWidth - buttonConfig.getWidth() / 2 - 90, buttonPlay.getHeight() + 50 + buttonAudio.getHeight() + 25);

        stage.act(Gdx.graphics.getDeltaTime());

        game.batch.viewportExtendApply();
        stage.draw();

        game.batch.begin();
        font.draw(game.batch, Constants.VERSAO, 0, 15);
        game.batch.end();

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
        if (!inicializado) {
            animationInicialize();
            inicializado = true;
            Gdx.input.setInputProcessor(stage);
        }

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    void goNextScreen() {
        Gdx.app.debug(TAG, "vai para tela niveis");
        if (inicializado) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    animationFinalize();
                    inicializado = false;
                }
            }).start();
        }
    }

    void loadComponents() {
        font = Assets.instance.fonts.defaultNormal;

        buttonConfig = new ImageButton(new TextureRegionDrawable(Assets.instance.button.bc));
        buttonAudio = new ImageButton(new TextureRegionDrawable(Assets.instance.button.ba));
        buttonAudioUpdate();
        buttonPlay = new ImageButton(new TextureRegionDrawable(Assets.instance.button.bp));

        pers = new Image(Assets.instance.pers.pers);
        fundo = new Image(Assets.instance.telaFundo.telaFundo);
        arvEsq = new Image(Assets.instance.arvEsq.arvEsq);
        arvDir = new Image(Assets.instance.arvDir.arvDir);
        titulo = new Image(Assets.instance.title.title);

        stage.addActor(fundo);
        stage.addActor(arvEsq);
        stage.addActor(arvDir);
        stage.addActor(titulo);
        stage.addActor(pers);
        stage.addActor(buttonConfig);
        stage.addActor(buttonAudio);
        stage.addActor(buttonPlay);
    }

    void loadListener() {
        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                goNextScreen();
            }
        });

        buttonConfig.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                SubScreenConfig subScreenConfig = new SubScreenConfig(game);
                stage.addActor(subScreenConfig);
                subScreenConfig.addListener(new WindowListener() {
                    @Override
                    public void closeSuccess(WindowListener.CloseEvent event, Actor actor) {
                        buttonAudioUpdate();
                    }

                    @Override
                    public void closeNoSuccess(WindowListener.CloseEvent event, Actor actor) {
                        Gdx.app.debug(SubScreenConfig.TAG, "close");
                    }
                });
            }
        });

        buttonAudio.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                GamePreferences.instance.audioLig = !GamePreferences.instance.audioLig;
                GamePreferences.instance.save();
                buttonAudioUpdate();
            }
        });

    }
    
    void buttonAudioUpdate() {
        ImageButtonStyle buttonAudioStyle;
        if (GamePreferences.instance.audioLig) {
            buttonAudioStyle = new ImageButtonStyle(null, null, null,
                    new TextureRegionDrawable(Assets.instance.button.ba), null, null);
        } else {
            buttonAudioStyle = new ImageButtonStyle(null, null, null,
                    new TextureRegionDrawable(Assets.instance.button.bna), null, null);
        }
        buttonAudio.setStyle(buttonAudioStyle);
    }
    
     private void animationInicialize() {
        arvDir.getColor().a = 0;
        buttonAudio.getColor().a = 0;
        buttonConfig.getColor().a = 0;
        buttonPlay.getColor().a = 0;

        // Animation
        MoveToAction fundoMovTo = Actions.moveTo(1, 1, 1, Interpolation.linear);
        ScaleToAction fundoScaleTo = Actions.scaleTo(1, 1, 1, Interpolation.linear);

        ParallelAction fundoAction = new ParallelAction();
        fundoAction.addAction(fundoMovTo);
        fundoAction.addAction(fundoScaleTo);

        AlphaAction arvDirAlpha = Actions.fadeIn(0.1f);
        arvDirAlpha.setTarget(arvDir);

        AlphaAction buttonAudioAlpha = Actions.fadeIn(0.1f);
        buttonAudioAlpha.setTarget(buttonAudio);

        AlphaAction buttonConfigAlpha = Actions.fadeIn(0.1f);
        buttonConfigAlpha.setTarget(buttonConfig);

        AlphaAction buttonPlayAlpha = Actions.fadeIn(0.1f);
        buttonPlayAlpha.setTarget(buttonPlay);

        ParallelAction buttonsAction = new ParallelAction(arvDirAlpha, buttonAudioAlpha, buttonConfigAlpha, buttonPlayAlpha);

        ScaleToAction buttonPosPlayScaleTo1 = Actions.scaleTo(1.2f, 1.2f, 0.5f);
        buttonPosPlayScaleTo1.setTarget(buttonPlay);
        ScaleToAction buttonPosPlayScaleTo2 = Actions.scaleTo(1f, 1f, 0.5f);
        buttonPosPlayScaleTo2.setTarget(buttonPlay);

        SequenceAction buttonPosPlay = new SequenceAction();
        buttonPosPlay.addAction(buttonPosPlayScaleTo1);
        buttonPosPlay.addAction(buttonPosPlayScaleTo2);

        RepeatAction buttonPosPlayInfiniteLoop = new RepeatAction();
        buttonPosPlayInfiniteLoop.setCount(RepeatAction.FOREVER);
        buttonPosPlayInfiniteLoop.setAction(buttonPosPlay);

        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(fundoAction);
        sequenceAction.addAction(buttonsAction);
        sequenceAction.addAction(buttonPosPlayInfiniteLoop);

        stage.getRoot().addAction(sequenceAction);
    }

    private void animationFinalize() {
        //Animation
        ParallelAction fundoAction = new ParallelAction();
        fundoAction.addAction(Actions.moveTo(game.batch.viewportExtendWidth - 3237, -490, 1));
        fundoAction.addAction(Actions.scaleTo(2.7f, 2.7f, 1));

        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(fundoAction);

        sequenceAction.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                inicializado = false;
                game.setScreen(new ScreenLevel(game));
            }
        }));

        stage.getRoot().addAction(sequenceAction);

        arvDir.addAction(Actions.fadeOut(0.1f));
        buttonAudio.addAction(Actions.fadeOut(0.1f));
        buttonConfig.addAction(Actions.fadeOut(0.1f));
        buttonPlay.addAction(Actions.fadeOut(0.1f));

        //buttonPlay.clearListeners();
    }

}
