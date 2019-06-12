/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.rnarcfec.game.util.Constants;

/**
 *
 * @author RNR
 */
public class Assets implements Disposable, AssetErrorListener {

    private static final String TAG = Assets.class.getName();
    //public static final float ANIM_ARCO_FRAME_RATE = 1 / 60f;
    //public static final float ANIM_BEXIGA_FRAME_RATE = 1 / 20f;

    private AssetManager assetManager;
    public static final Assets instance = new Assets();

    public AssetFlecha flecha;
    public AssetArcoPernas arcoPernas;
    public AssetArcoCorpo arcoCorpo;
    public AssetBexiga bexiga;
    public AssetFonts fonts;
    public AssetGrama grama;

    public AssetTelaFundo telaFundo;
    public AssetPers pers;
    public AssetArvEsq arvEsq;
    public AssetArvDir arvDir;
    public AssetButton button;
    public AssetTitle title;

    public AssetPlaca placa;
    public AssetSubTelaConfig subTelaConfig;
    //public AssetSkinUser skinUser;

    private Assets() {
    }

    void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        // set asset manager error handler
        assetManager.setErrorListener(this);

        // load texture atlas
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        assetManager.load(Constants.TEXTURE_GRAMA, Texture.class);
        assetManager.load(Constants.TEXTURE_TELA_FUNDO, Texture.class);
        //assetManager.load(Constants.TEXTURE_ATLAS_SUB_CONFIG, TextureAtlas.class);
        // start loading assets and wait until finished
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        flecha = new AssetFlecha(atlas);
        bexiga = new AssetBexiga(atlas);
        arcoPernas = new AssetArcoPernas(atlas);
        arcoCorpo = new AssetArcoCorpo(atlas);
        fonts = new AssetFonts();

        Texture texGrama = assetManager.get(Constants.TEXTURE_GRAMA);
        grama = new AssetGrama(texGrama);

        Texture texTelaFundo = assetManager.get(Constants.TEXTURE_TELA_FUNDO);
        telaFundo = new AssetTelaFundo(texTelaFundo);

        pers = new AssetPers(atlas);
        arvEsq = new AssetArvEsq(atlas);
        arvDir = new AssetArvDir(atlas);
        button = new AssetButton(atlas);

        title = new AssetTitle(atlas);
        placa = new AssetPlaca(atlas);

        TextureAtlas atlasSubConfig = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

        subTelaConfig = new AssetSubTelaConfig(atlasSubConfig);
        //skinUser = new AssetSkinUser();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        fonts.defaultNormal.dispose();
        fonts.gameScreen.dispose();
        fonts.gameScreenTitulo.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Nao foi posivel carregar o asset '" + asset.fileName + "'", throwable);
    }

    public class AssetFlecha {

        public final AtlasRegion flecha;

        public AssetFlecha(TextureAtlas atlas) {
            flecha = atlas.findRegion("Arco/flecha");
        }
    }

    public class AssetArcoPernas {

        public final Animation<TextureRegion> animArcoPernas;
        //public final TextureRegion arco;

        public AssetArcoPernas(TextureAtlas atlas) {
            Array<AtlasRegion> regions = atlas.findRegions("Arco/ArcoAniPernas");
            animArcoPernas = new Animation(1, regions, Animation.PlayMode.LOOP);
        }
    }

    public class AssetArcoCorpo {

        public final Animation<TextureRegion> animArcoCorpo;
        //public final TextureRegion arco;

        public AssetArcoCorpo(TextureAtlas atlas) {
            Array<AtlasRegion> regions = atlas.findRegions("Arco/ArcoAniCorpo");
            animArcoCorpo = new Animation(1, regions, Animation.PlayMode.LOOP);
        }
    }

    public class AssetBexiga {

        public final Animation<TextureRegion> animBexiga;

        public AssetBexiga(TextureAtlas atlas) {
            Array<AtlasRegion> regions = atlas.findRegions("Bexiga/BexigaAniEstouro");
            animBexiga = new Animation(1, regions, Animation.PlayMode.NORMAL);

        }
    }

    public class AssetFonts {

        public final BitmapFont defaultNormal;
        public final BitmapFont gameScreen;
        public final BitmapFont gameScreenTitulo;
        public AssetFonts() {
            defaultNormal = new BitmapFont();
            gameScreen = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
            gameScreenTitulo = new BitmapFont(Gdx.files.internal("fonts/font2.fnt"));
        }
    }

    public class AssetGrama {

        public final Texture grama;

        public AssetGrama(Texture texture) {
            grama = texture;
        }

    }

    public class AssetTelaFundo {

        public final Texture telaFundo;

        public AssetTelaFundo(Texture texture) {
            telaFundo = texture;
        }

    }

    public class AssetPers {

        public final AtlasRegion pers;

        public AssetPers(TextureAtlas atlas) {
            pers = atlas.findRegion("Telas/pers");
        }

    }

    public class AssetArvEsq {

        public final AtlasRegion arvEsq;

        public AssetArvEsq(TextureAtlas atlas) {
            arvEsq = atlas.findRegion("Telas/arv_esq");
        }

    }

    public class AssetArvDir {

        public final AtlasRegion arvDir;

        public AssetArvDir(TextureAtlas atlas) {
            arvDir = atlas.findRegion("Telas/arv_dir");
        }

    }

    public class AssetButton {

        public final AtlasRegion ba;
        public final AtlasRegion bna;
        public final AtlasRegion bp;
        public final AtlasRegion bc;
        public final AtlasRegion bv;
        public final AtlasRegion bvB;

        public AssetButton(TextureAtlas atlas) {
            ba = atlas.findRegion("Telas/ba");
            bna = atlas.findRegion("Telas/bna");
            bp = atlas.findRegion("Telas/bp");
            bc = atlas.findRegion("Telas/bc");
            bv = atlas.findRegion("Telas/bv");
            bvB = atlas.findRegion("Telas/bvB");
            
        }

    }

    public class AssetSubTelaConfig {

        //public final  TextureAtlas atlasSubConfig;
        public final AtlasRegion janelaConfig;
        public final AtlasRegion iconeSom;
        public final AtlasRegion iconeMusic;
        public final AtlasRegion iconeVibr;
        public final AtlasRegion barra;
        public final AtlasRegion knob;
        public final AtlasRegion botao;
        public final AtlasRegion fundo;
        
        public final AtlasRegion janelaVitoria;
        public final AtlasRegion botaoContinuar;
        public final AtlasRegion botaoProximo;
        public final AtlasRegion botaoSair;
        public final AtlasRegion botaoTentarNovamente;
        public final AtlasRegion janelaPause;
        public final AtlasRegion iconeEstrela;
        public final AtlasRegion iconePontos;
        public final AtlasRegion iconeFlechas;

        public AssetSubTelaConfig(TextureAtlas atlas) {
            //atlasSubConfig = atlas;
            janelaConfig = atlas.findRegion("SubTelas/janelaConfig");
            iconeSom = atlas.findRegion("SubTelas/iconeSom");
            iconeMusic = atlas.findRegion("SubTelas/iconeMusica");
            iconeVibr = atlas.findRegion("SubTelas/IconeVibra");
            barra = atlas.findRegion("SubTelas/barra");
            knob = atlas.findRegion("SubTelas/knob");
            botao = atlas.findRegion("SubTelas/botao");
            fundo = atlas.findRegion("SubTelas/fundo");
            
            janelaPause = atlas.findRegion("SubTelas/janelaPause");
            janelaVitoria = atlas.findRegion("SubTelas/janelaVitoria");
            botaoContinuar = atlas.findRegion("SubTelas/botaoContinuar");
            botaoProximo = atlas.findRegion("SubTelas/botaoProximo");
            botaoTentarNovamente = atlas.findRegion("SubTelas/botaoTentarNovamente");
            botaoSair = atlas.findRegion("SubTelas/botaoSair");
            iconeEstrela = atlas.findRegion("SubTelas/iconeEstrela");
            iconePontos = atlas.findRegion("SubTelas/iconePontos");
            iconeFlechas = atlas.findRegion("SubTelas/iconeFlechas");
            
        }

    }

    public class AssetPlaca {

        public final Animation<TextureRegion> placa;
        public final AtlasRegion placaB;

        public AssetPlaca(TextureAtlas atlas) {
            Array<AtlasRegion> regions = atlas.findRegions("Telas/placaAni/placas");
            placa = new Animation(1, regions, Animation.PlayMode.NORMAL);
            placaB = atlas.findRegion("Telas/PlacaB");
        }

    }

    public class AssetTitle {

        public final AtlasRegion title;

        public AssetTitle(TextureAtlas atlas) {
            title = atlas.findRegion("Telas/titulo");
        }

    }

    /*
    public class AssetSkinUser{

        public final Skin skinUser;

        public AssetSkinUser(){
            skinUser = new Skin();
        }
    }
     */
}
