/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens.sub;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.rnarcfec.game.Assets;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.util.Linguagem;

/**
 *
 * @author RNR
 */
public class SubScreenVitoria extends AbstractSubScreen{
    Image janela;
    Label titulo;
    Table itens;
    ImageButton iconeFlechas;
    ImageButton iconePontos;
    ImageButton iconeEstrela_1;
    ImageButton iconeEstrela_2;
    ImageButton iconeEstrela_3;
    ImageButton iconeEstrela_4;
    ImageButton iconeEstrela_5;
    Label numFlechas;
    Label numPontos;
    ImageButton botaoProximo;
    ImageButton botaoVoltar;
    Label textBotaoProximo;
    Label textBotaoVoltar;
    
    public SubScreenVitoria(RnArcFecGame game) {
        super(game);
        janela = new Image(Assets.instance.subTelaConfig.janelaVitoria);
        super.setHeight(janela.getHeight());
        super.setWidth(janela.getWidth());

        Label.LabelStyle labelStyleTitulo = new Label.LabelStyle();
        labelStyleTitulo.font = Assets.instance.fonts.gameScreen;
        labelStyleTitulo.fontColor = new Color(0xb27c73ff);
        labelStyleTitulo.font.getData().setScale(1.5f);
        titulo = new Label(Linguagem.VITORIA, labelStyleTitulo);
        titulo.setAlignment(Align.center);

        Label.LabelStyle labelStyleNum = new Label.LabelStyle();
        labelStyleNum.font = Assets.instance.fonts.defaultNormal;
        labelStyleNum.fontColor = new Color(0xb27c73ff);
        labelStyleNum.font.getData().setScale(2f);
        
        
        iconeFlechas = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.iconeFlechas));
        iconePontos = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.iconePontos));
        numFlechas = new Label("10", labelStyleNum);
        numPontos = new Label("5000", labelStyleNum);

        iconeEstrela_1 = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.iconeEstrela));
        iconeEstrela_2 = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.iconeEstrela));
        iconeEstrela_3 = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.iconeEstrela));
        iconeEstrela_4 = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.iconeEstrela));
        iconeEstrela_5 = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.iconeEstrela));

        botaoProximo = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.botaoProximo));
        botaoVoltar = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.botaoSair));
        textBotaoProximo = new Label(Linguagem.PROXIMO_NIVEL, labelStyleNum);
        textBotaoProximo.setAlignment(Align.center);
        textBotaoVoltar = new Label(Linguagem.VOLTAR, labelStyleNum);
        textBotaoVoltar.setAlignment(Align.center);
        
        itens = new Table();
        //itens.debugAll();
        itens.defaults().width(65);
        itens.center().top();
        itens.setPosition(janela.getWidth() / 2, janela.getHeight() - 140);
        
        itens.add(titulo).colspan(5);
        itens.row().padTop(20);
        itens.add(iconeFlechas);
        itens.add(numFlechas);
        itens.add();
        itens.add(iconePontos);
        itens.add(numPontos);
        itens.row().padTop(10);
        itens.add(iconeEstrela_1);
        itens.add(iconeEstrela_2);
        itens.add(iconeEstrela_3);
        itens.add(iconeEstrela_4);
        itens.add(iconeEstrela_5);
        itens.row().padTop(20);
        itens.columnDefaults(0).colspan(2).width(150).center();
        itens.columnDefaults(3).colspan(2).width(150).center();
        itens.add(botaoProximo);
        itens.add();
        itens.add(botaoVoltar);
        itens.row();
        itens.add(textBotaoProximo);
        itens.add();
        itens.add(textBotaoVoltar);

        super.addActor(janela);
        super.addActor(itens);
    }
    
}
