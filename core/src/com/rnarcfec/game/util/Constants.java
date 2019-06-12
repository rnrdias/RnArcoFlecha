/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.util;

/**
 *
 * @author RNR
 */
public class Constants {

     public static final String VERSAO = "1.0";
     public static final String PREFERENCES = "RnAcrFecGame.preferences";
    
     public final static int GAME_WIDTH = 1280;
     public final static int GAME_HEIGHT = 720;

     public final static int START_LIVES = 3;

     public final static String TEXTURE_ATLAS_OBJECTS = "GameScreen/pack.atlas";
    //final public static String TEXTURE_ATLAS_SUB_CONFIG = "GameScreen/subConfig.atlas";
     public final static String TEXTURE_GRAMA = "GameScreen/grama.png";

     public final static String TEXTURE_TELA_FUNDO = "GameScreen/telaFundo.png";
     public final static boolean DEBUG_GRAFICOS = true;
     public final static boolean DEBUG = true;

    public static final float BEXIGA_VELOC = 100;
    public static final float FLECHA_VELOC = 300;
    public static final float ARCO_VELOC = 200;
    public static final float ARCO_GRADE = 50;
    public static final float ARCO_MAX_X = GAME_WIDTH / 2;

    //animacoes
    public static final float ANIM_ARCO_FRAME_RATE = 1 / 60f;
    public static final float ANIM_PERNAS_FRAME_RATE = 1 / 40f;
    public static final float ANIM_BEXIGA_FRAME_RATE = 1 / 20f;
    public static final float ANIM_PLACA_FRAME_RATE = 1 / 40f;

}
