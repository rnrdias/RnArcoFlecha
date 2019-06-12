package com.rnarcfec.game;

import com.badlogic.gdx.Application;
import com.rnarcfec.game.util.BatchView;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rnarcfec.game.screens.ScreenMainMenu;
import com.rnarcfec.game.util.Constants;
import java.util.Stack;

public class RnArcFecGame extends Game {

    private Stack<Screen> screens;
    private final String TAG = RnArcFecGame.class.getName();
    //private Screen screenPrevious;
    public BatchView batch;
    public OrthographicCamera camera;
    public FitViewport viewport;
    public FitViewport viewportExtend;
    public BitmapFont fontDefault;
    public int gameNivel;
    
    @Override
    public void create() {

        if (Constants.DEBUG) {
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
        }
        screens = new Stack<Screen>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        camera.position.x = Constants.GAME_WIDTH / 2;
        camera.position.y = Constants.GAME_HEIGHT / 2;

        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, camera);
        viewportExtend = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, camera);
        batch = new BatchView(viewport, viewportExtend);
        //Load Assets
        Assets.instance.init(new AssetManager());
        fontDefault = Assets.instance.fonts.defaultNormal;
        
        //load Preferences
        GamePreferences.instance.load();
        
        this.setScreen(new ScreenMainMenu(this));
        //this.setScreen(new ScreenTest(this));
        
        gameNivel = 0;
    }
    
    /*
    Selecionar nova screen
    */
    @Override
    public void setScreen(Screen screen) {
        screens.push(screen);
        super.setScreen(screen);
    }

    /*
    voltar para tela anterior
     */
    public void setScreenPrevious() {
        screens.pop().dispose();
        super.setScreen(screens.lastElement());
        screens.lastElement().resume();
        render();
        
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //viewport.apply();
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        this.getScreen().dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        if (width * ((float) Constants.GAME_HEIGHT / Constants.GAME_WIDTH) > height) {
            viewportExtend.update(width, (int) (((float) width / Constants.GAME_WIDTH) * Constants.GAME_HEIGHT));
            batch.viewportExtendHeight = (Constants.GAME_WIDTH / ((float) width / height));
            batch.viewportExtendWidth = Constants.GAME_WIDTH;
            //System.out.println("a: " + batch.a + "width:" + width + " camera.viewportHeight:" + camera.viewportHeight + "1/:" + (((float) width / Constants.GAME_WIDTH)));
        } else {
            viewportExtend.update((int) (((float) height / Constants.GAME_HEIGHT) * Constants.GAME_WIDTH), height);
            batch.viewportExtendHeight = Constants.GAME_HEIGHT;
            batch.viewportExtendWidth = (Constants.GAME_HEIGHT / ((float) height / width));
        }

        Gdx.app.debug(TAG, "width: " + width + " height: " + height + " viewportExtendHeight: " + batch.viewportExtendHeight + " viewportExtendWidth: " + batch.viewportExtendWidth);

    }

}
