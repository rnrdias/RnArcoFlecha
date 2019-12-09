/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rnarcfec.game.util.BatchView;
import com.rnarcfec.game.util.Constants;
import com.rnarcfec.game.util.Debug;

/**
 *
 * @author RNR
 */
public class AbstractGameRectangle {

    //protected Vector2 position;
    protected Rectangle bounds;
    protected Vector2 offset;
    private final Debug debug;

    public AbstractGameRectangle(float offsetX, float offsetY, float width, float height) {
        offset = new Vector2();
        bounds = new Rectangle();

        offset.x = offsetX;
        offset.y = offsetY;
        bounds.height = height;
        bounds.width = width;
        bounds.x = offsetX;
        bounds.y = offsetY;

        debug = new Debug();
    }

    public void setX(float x) {
        bounds.x = x + offset.x;
    }

    public void setY(float y) {
        bounds.y = y + offset.y;
    }

    public float getX() {
        return bounds.x - offset.x;
    }

    public float getY() {
        return bounds.y - offset.y;
    }

    public float getWidth() {
        return bounds.width;
    }

    public float getHeight() {
        return bounds.height;
    }

    public boolean overlaps(AbstractGameRectangle gameRectangle) {
        return bounds.overlaps(gameRectangle.bounds);
    }

    public void render(BatchView batch) {
        if (Constants.DEBUG_GRAFICOS) {
            debug.Draw(batch, bounds);
        }
    }
}
