/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author RNR
 */
public class Debug {

    public Sprite debugQ;
    public Texture debugQTex;
    
    
    public Debug() {
        debugQTex = new Texture(Gdx.files.internal("Debug/quadrado.png"));
        debugQ = new Sprite(debugQTex);
        debugQ.setAlpha(0.5f);
    }

    public void Draw(Batch batch, Rectangle rectangle) {
        debugQ.setX(rectangle.getX());
        debugQ.setY(rectangle.getY());
        debugQ.setSize(rectangle.getWidth(), rectangle.getHeight());
        debugQ.draw(batch);
        
    }
    
    
    public void dispose(){
        debugQTex.dispose();
    }
}
