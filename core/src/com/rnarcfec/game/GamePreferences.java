/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.rnarcfec.game.util.Constants;

/**
 *
 * @author RNR
 */
public class GamePreferences {

    public static final String TAG = GamePreferences.class.getName();
    public static final GamePreferences instance = new GamePreferences();
    private final Preferences prefs;

    public boolean audioLig;
    public float vibraIdenc;
    public float musicVol;
    public float audioVol;
    public int pontuacao;

    // singleton: prevent instantiation from other classes
    private GamePreferences() {
        prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
    }

    public void load() {
        audioLig = prefs.getBoolean("audioLig", true);
        vibraIdenc = MathUtils.clamp(prefs.getFloat("vibraLig", 100), 0, 100);
        audioVol = MathUtils.clamp(prefs.getFloat("audioVol", 100), 0, 100);
        musicVol = MathUtils.clamp(prefs.getFloat("musicVol", 100), 0, 100);
        pontuacao = prefs.getInteger("pontuacao", 0);
    }

    public void save() {
        prefs.putBoolean("audioLig", audioLig);
        prefs.putFloat("vibraLig", vibraIdenc);
        prefs.putFloat("audioVol", audioVol);
        prefs.putFloat("musicVol", musicVol);
        prefs.putInteger("pontuacao", pontuacao);
        prefs.flush();
    }
}
