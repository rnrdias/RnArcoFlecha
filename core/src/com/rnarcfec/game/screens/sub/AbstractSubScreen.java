/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens.sub;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.screens.util.WindowListener;

/**
 *
 * @author RNR
 */
public abstract class AbstractSubScreen extends Group {

    protected final RnArcFecGame game;
    private final Image img;


    public AbstractSubScreen(final RnArcFecGame game) {
        this.game = game;

        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0.7f);
        pixmap.fillRectangle(0, 0, 64, 64);
        Texture pixmaptex = new Texture(pixmap);
        pixmap.dispose();

        img = new Image(pixmaptex);
        img.setScale(2000);
        img.setOrigin(Align.center);
        img.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y); //To change body of generated methods, choose Tools | Templates.
                close(false);
            }
        });

        super.addActor(img);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        super.setX(game.batch.viewportExtendWidth / 2 - this.getWidth() / 2);
        super.setY(game.batch.viewportExtendHeight - this.getHeight());
    }

    public void close(boolean success) {
        WindowListener.CloseEvent eventClose = new WindowListener.CloseEvent();
        //eventClose.setStage(this.getStage());
        eventClose.setSuccess(success);
        for (EventListener event : this.getListeners()) {
            event.handle(eventClose);
        }
        this.remove();
    }
}
