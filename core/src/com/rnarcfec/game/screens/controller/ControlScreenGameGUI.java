/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens.controller;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.util.Linguagem;

/**
 *
 * @author RNR
 */
public class ControlScreenGameGUI {

    private int contPontos;
    private int contVidas;
    private int contFlechas;

    final RnArcFecGame game;
    BitmapFont fontDefault;

    public ControlScreenGameGUI(RnArcFecGame game, int contPontos, int contVidas, int contFlechas) {
        this.game = game;
        this.contPontos = contPontos;
        this.contVidas = contVidas;
        this.contFlechas = contFlechas;
        fontDefault = game.fontDefault;
    }

    public void update(float deltaTime) {

    }

    public void render() {

        game.batch.viewportExtendApply();
        fontDefault.draw(game.batch, Linguagem.GAME_PONTOS + contPontos, 0, game.batch.viewportExtendHeight);
        fontDefault.draw(game.batch, Linguagem.GAME_FLECHAS + contFlechas, 150, game.batch.viewportExtendHeight);
        
        
        fontDefault.draw(game.batch, Linguagem.GAME_VIDAS + contVidas, game.batch.viewportExtendWidth-50, game.batch.viewportExtendHeight);
        game.batch.flush();
        //System.out.println(""+game.batch.a);
        game.batch.viewportApply();
    }

    public void addPontos(int pontos) {
        contPontos += pontos;
    }

    public void addFlechas(int flechas) {
        contFlechas += flechas;
    }

    /*
    Remove uma flecha
     */
    public void removeFlecha() {
        if (contFlechas > 0) {
            contFlechas--;
        }
    }
    
    /*
    Remove uma vida
     */
    public void removeVida() {
        if (contVidas > 0) {
            contVidas--;
        }
    }

    public int getQuantFlechas() {
        return contFlechas;
    }

    public boolean isFlechaZero() {
        return contFlechas <= 0;
    }

    public boolean isVidaZero() {
        return contVidas <= 0;
    }
}
