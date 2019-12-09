/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens.util;

import com.badlogic.gdx.math.Interpolation;

/**
 *
 * @author RNR
 */
public class ScaleTest extends Interpolation {

    @Override
    public float apply(float a) {
        //Gdx.app.debug("teste " + 23.7556561086 * Math.pow(a, 4), "a:" + a + "r:" + (Math.pow(23.7556561086 * a, 4) - Math.pow(44.5135746606 * a, 3) + Math.pow(22.1606334842 * a, 2) - 0.402714932127 * a));
        return (float) (23.7556561086 * Math.pow(a, 4) - 44.5135746606 * Math.pow(a, 3) + 22.1606334842 * Math.pow(a, 2) - 0.402714932127 * a);
    }

}
