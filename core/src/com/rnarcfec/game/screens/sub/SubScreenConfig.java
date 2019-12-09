/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens.sub;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.rnarcfec.game.Assets;
import com.rnarcfec.game.GamePreferences;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.util.Linguagem;

/**
 *
 * @author RNR
 */
public class SubScreenConfig extends AbstractSubScreen {

    public static final String TAG = SubScreenConfig.class.getName();

    ImageButton janela;
    Label titulo;
    Button iconeSom;
    Button iconeMusic;
    Button iconeVibr;
    Slider barraSom;
    Slider barraMusic;
    Slider barraVibr;
    Button botaoOk;

    Table itens;

    public SubScreenConfig(final RnArcFecGame game) {
        super(game);

        janela = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.janelaConfig));
        super.setHeight(janela.getHeight());
        super.setWidth(janela.getWidth());
        
        
        Label.LabelStyle labelStyleTitulo = new Label.LabelStyle();
        labelStyleTitulo.font = Assets.instance.fonts.gameScreenTitulo;
        labelStyleTitulo.fontColor = new Color(0xb27c73ff);
        labelStyleTitulo.font.getData().setScale(1);
        titulo = new Label(Linguagem.CONFIG, labelStyleTitulo);
        titulo.setAlignment(Align.center);

        iconeSom = new Button(new TextureRegionDrawable(Assets.instance.subTelaConfig.iconeSom));
        iconeMusic = new Button(new TextureRegionDrawable(Assets.instance.subTelaConfig.iconeMusic));
        iconeVibr = new Button(new TextureRegionDrawable(Assets.instance.subTelaConfig.iconeVibr));

        Slider.SliderStyle styleBarra = new Slider.SliderStyle(new TextureRegionDrawable(Assets.instance.subTelaConfig.barra), new TextureRegionDrawable(Assets.instance.subTelaConfig.knob));

        barraSom = new Slider(0, 100, 1, false, styleBarra);
        barraMusic = new Slider(0, 100, 1, false, styleBarra);
        barraVibr = new Slider(0, 100, 1, false, styleBarra);

        Label.LabelStyle labelStyleOk = new Label.LabelStyle();
        labelStyleOk.font = Assets.instance.fonts.gameScreen;
        labelStyleOk.fontColor = new Color(0xb27c73ff);
        labelStyleOk.font.getData().setScale(1f);
        Label botaoOkLabel = new Label(Linguagem.OK, labelStyleOk);
        botaoOk = new Button(new TextureRegionDrawable(Assets.instance.subTelaConfig.botao));
        botaoOk.add(botaoOkLabel);

        itens = new Table();

        itens.columnDefaults(0).pad(10);
        itens.columnDefaults(1).width(200);
        itens.center().top();
        itens.setPosition(janela.getWidth() / 2, janela.getHeight() - 140);

        itens.add(titulo).colspan(2);
        itens.row().padTop(20);
        itens.add(iconeSom);
        itens.add(barraSom);
        itens.row();
        itens.add(iconeMusic);
        itens.add(barraMusic);
        itens.row();
        itens.add(iconeVibr);
        itens.add(barraVibr);
        itens.row();
        itens.add(botaoOk).colspan(2).pad(20);

        super.addActor(janela);
        super.addActor(itens);

        if (GamePreferences.instance.audioLig) {
            barraSom.setValue(GamePreferences.instance.audioVol);
            barraMusic.setValue(GamePreferences.instance.musicVol);
            barraVibr.setValue(GamePreferences.instance.vibraIdenc);
        }

        botaoOk.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                GamePreferences.instance.audioVol = barraSom.getValue();
                GamePreferences.instance.musicVol = barraMusic.getValue();
                GamePreferences.instance.vibraIdenc = barraVibr.getValue();
                GamePreferences.instance.audioLig = true;
                GamePreferences.instance.save();
                close(true);
            }
        });

    }
}
