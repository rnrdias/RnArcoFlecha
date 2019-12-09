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
public class SubScreenPause extends AbstractSubScreen {

    private final Image janela;
    private final Label titulo;
    private final Table itens;
    private final ImageButton botaoContinuar;
    private final ImageButton botaoSair;
    private final Label textBotaoContinuar;
    private final Label textBotaoSair;

    public SubScreenPause(RnArcFecGame game) {
        super(game);
        janela = new Image(Assets.instance.subTelaConfig.janelaPause);
        super.setHeight(janela.getHeight());
        super.setWidth(janela.getWidth());

        Label.LabelStyle labelStyleTitulo = new Label.LabelStyle();
        labelStyleTitulo.font = Assets.instance.fonts.gameScreen;
        labelStyleTitulo.fontColor = new Color(0xb27c73ff);
        labelStyleTitulo.font.getData().setScale(1.5f);
        titulo = new Label(Linguagem.PAUSE, labelStyleTitulo);
        titulo.setAlignment(Align.center);

        Label.LabelStyle labelStyleNum = new Label.LabelStyle();
        labelStyleNum.font = Assets.instance.fonts.defaultNormal;
        labelStyleNum.fontColor = new Color(0xb27c73ff);
        labelStyleNum.font.getData().setScale(2f);

        botaoContinuar = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.botaoContinuar));
        botaoSair = new ImageButton(new TextureRegionDrawable(Assets.instance.subTelaConfig.botaoSair));
        textBotaoContinuar = new Label(Linguagem.CONTINUAR, labelStyleNum);
        textBotaoContinuar.setAlignment(Align.center);
        textBotaoSair = new Label(Linguagem.VOLTAR, labelStyleNum);
        textBotaoSair.setAlignment(Align.center);

        itens = new Table();
        //itens.debugAll();
        itens.center().top();
        itens.setPosition(janela.getWidth() / 2, janela.getHeight() - 140);

        itens.add(titulo).colspan(3);
        itens.row().padTop(20);
        itens.add(botaoContinuar);
        itens.add().width(45);
        itens.add(botaoSair);
        itens.row();
        itens.add(textBotaoContinuar);
        itens.add();
        itens.add(textBotaoSair);

        super.addActor(janela);
        super.addActor(itens);

    }

}
