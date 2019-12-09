/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 *
 * @author RNR
 */
public class BatchView extends SpriteBatch {

    private final FitViewport viewport;
    private final FitViewport viewportExtend;
    public float viewportExtendHeight;
    public float viewportExtendWidth;
    
    public BatchView(FitViewport viewport, FitViewport viewportExtend) {
        this.viewport = viewport;
        this.viewportExtend = viewportExtend;
    }
    
    
    public void viewportApply(){
        this.flush();
        viewport.apply();
    }
    
    public void viewportExtendApply(){
        this.flush();
        viewportExtend.apply();
    }
    
}
