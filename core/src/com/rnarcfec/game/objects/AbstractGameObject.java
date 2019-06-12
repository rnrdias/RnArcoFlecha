/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rnarcfec.game.util.BatchView;

/**
 *
 * @author RNR
 */
public abstract class AbstractGameObject extends AbstractGameRectangle {

    private final TextureAtlas.AtlasRegion atlasRegion;


    public AbstractGameObject(TextureAtlas.AtlasRegion atlasRegion, float offsetX, float offsetY, float width, float height) {
        super(offsetX, offsetY, width, height);
        this.atlasRegion = atlasRegion;
    }

    @Override
    public void render(BatchView batch) {
        batch.draw(atlasRegion, getX(), getY());
        super.render(batch);
    }

}
