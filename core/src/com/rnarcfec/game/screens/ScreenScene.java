/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//teste
package com.rnarcfec.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rnarcfec.game.Assets;
import com.rnarcfec.game.RnArcFecGame;

/**
 *
 * @author RNR
 */
public class ScreenScene implements Screen {

    final RnArcFecGame game;
    private Stage stage;
    TextButton button1;
    Button b;
    TextureRegion upRegion;

    public ScreenScene(final RnArcFecGame game) {
        this.game = game;

        //stage = new Stage();
        stage = new Stage(game.viewport, game.batch);
        //stage.setViewport(game.viewportExtend);
        Gdx.input.setInputProcessor(stage);

        /*Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);*/
        //table.setDebug(true);
        upRegion = Assets.instance.button.ba;
        TextureRegion downRegion = Assets.instance.button.ba;
        BitmapFont buttonFont = Assets.instance.fonts.defaultNormal;

        TextButtonStyle style = new TextButtonStyle();

        style.up = new TextureRegionDrawable(upRegion);
        style.down = new TextureRegionDrawable(downRegion);
        style.font = buttonFont;

        button1 = new TextButton("Button 1", style);
        b = new Button(style);

        MoveToAction action = new MoveToAction();
        action.setPosition(500, 500);
        action.setDuration(5);
        
        //button1.addAction(action);

        stage.addActor(button1);
        //stage.addActor(button1);
        //table.add(button1);
        TextButton button2 = new TextButton("Button 2", style);
        //table.add(button2);

        int X_left = Gdx.graphics.getWidth() / 3 - downRegion.getRegionWidth() / 2;
        int X_right = Gdx.graphics.getWidth() * 2 / 3 - downRegion.getRegionWidth() / 2;
        int Y_top = Gdx.graphics.getHeight() * 2 / 3 - downRegion.getRegionHeight() / 2;
        int Y_bottom = Gdx.graphics.getHeight() / 3 - downRegion.getRegionHeight() / 2;

        Image image1 = new Image(downRegion);
        image1.setPosition(X_left, Y_top);
        image1.setOrigin(image1.getWidth() / 2, image1.getHeight() / 2);
        stage.addActor(image1);

        ParallelAction topLeftRightParallelAction = new ParallelAction();
        topLeftRightParallelAction.addAction(Actions.moveTo(X_right, Y_top, 1, Interpolation.exp5Out));
        topLeftRightParallelAction.addAction(Actions.scaleTo(2, 2, 1, Interpolation.exp5Out));
        
        
        
        MoveToAction moveBottomRightAction = new MoveToAction();
        moveBottomRightAction.setPosition(X_right, Y_bottom);
        moveBottomRightAction.setDuration(1);
        moveBottomRightAction.setInterpolation(Interpolation.smooth);

        ParallelAction bottomLeftRightParallelAction = new ParallelAction();
        bottomLeftRightParallelAction.addAction(Actions.moveTo(X_left, Y_bottom, 1, Interpolation.sineOut));
        bottomLeftRightParallelAction.addAction(Actions.scaleTo(1, 1, 1));

        ParallelAction leftBottomTopParallelAction = new ParallelAction();
        leftBottomTopParallelAction.addAction(Actions.moveTo(X_left, Y_top, 1, Interpolation.swingOut));
        leftBottomTopParallelAction.addAction(Actions.rotateBy(90, 1));

        SequenceAction overallSequence = new SequenceAction();
        overallSequence.addAction(topLeftRightParallelAction);
        overallSequence.addAction(moveBottomRightAction);
        overallSequence.addAction(bottomLeftRightParallelAction);
        overallSequence.addAction(leftBottomTopParallelAction);

        RepeatAction infiniteLoop = new RepeatAction();
        infiniteLoop.setCount(RepeatAction.FOREVER);
        infiniteLoop.setAction(overallSequence);
        //image1.addAction(infiniteLoop);

        image1.setScale(0.5f);
        stage.setDebugAll(true);

        image1.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //super.touchUp(event, x, y, pointer, button); //To change body of generated methods, choose Tools | Templates.
                System.out.println("Changed!");
                //button1.setScale(0.5f);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //return super.touchDown(event, x, y, pointer, button); //To change body of generated methods, choose Tools | Templates.
                return true;
            }
        }
        );
        
        ImageButton buttonImage = new ImageButton(new TextureRegionDrawable(upRegion));
        stage.addActor(buttonImage);
        //buttonImage.setScale(0.2f);
        //buttonImage.getImage().setScale(0.2f);
    
        
        buttonImage.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                System.out.println("test");
            }
        });
        
        
        
        /*button1.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button); //To change body of generated methods, choose Tools | Templates.
                System.out.println("Changed!");
                //button1.setScale(0.5f);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //return super.touchDown(event, x, y, pointer, button); //To change body of generated methods, choose Tools | Templates.
                return true;
            }
        });*/
        button1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor
            ) {
                System.out.println("click");
            }
        }
        );
        
        button1.setScale(0.2f);
       
    }

    @Override
    public void render(float delta) {
        //game.batch.begin();
        //button1.setWidth(50);
        //button1.setHeight(50);
        button1.setScale(1f);
        //button1.setWidth(upRegion.getRegionWidth() * 0.5f);
        //button1.setHeight(upRegion.getRegionHeight() * 0.5f);
        button1.setPosition(game.batch.viewportExtendWidth - button1.getWidth(), 0);

        stage.act(Gdx.graphics.getDeltaTime());
        game.batch.viewportExtendApply();
        stage.draw();
        game.batch.viewportApply();
        //game.batch.end();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        //stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }

}
