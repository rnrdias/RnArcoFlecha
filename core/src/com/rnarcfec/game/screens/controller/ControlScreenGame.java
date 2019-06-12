/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnarcfec.game.screens.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.rnarcfec.game.RnArcFecGame;
import com.rnarcfec.game.objects.AbstractGameRectangle;
import com.rnarcfec.game.objects.ObjArco;
import com.rnarcfec.game.objects.ObjFlecha;
import com.rnarcfec.game.objects.ObjGrama;
import com.rnarcfec.game.screens.ScreenGameOver;
import com.rnarcfec.game.util.Constants;

/**
 *
 * @author RNR
 */
public abstract class ControlScreenGame {

    Array<ObjFlecha> flechas;
    ObjArco arco;
    ObjGrama grama;

    RnArcFecGame game;
    ControlScreenGameGUI controlScreenGameGUI;

    public ControlScreenGame(RnArcFecGame game, ControlScreenGameGUI controlScreenGameGUI) {
        this.game = game;
        this.controlScreenGameGUI = controlScreenGameGUI;
        arco = new ObjArco();
        flechas = new Array();
        grama = new ObjGrama();
    }

    //__________________________________Laco basico________________________________________
    public void update(float deltaTime) {
        controleFlechaUpdate(deltaTime);
        controleArcoUpdate(deltaTime);
    }

    public void render() {
        grama.render(game.batch);

        arco.render(game.batch);
        for (ObjFlecha fecha : flechas) {
            fecha.render(game.batch);
        }
    }

    //__________________________________Outras funçoes________________________________________
    public boolean overlaps(AbstractGameRectangle overlaps) {
        for (ObjFlecha flecha : flechas) {
            if (flecha.overlaps(overlaps)) {
                return true;
            }
        }
        return false;
    }

    private void criaFlecha() {
        if (controlScreenGameGUI.isFlechaZero()) {
            return;
        }

        ObjFlecha flecha = new ObjFlecha();
        flecha.setY(arco.getY());
        flechas.add(flecha);

        controlScreenGameGUI.removeFlecha();
    }

    private void controleFlechaUpdate(float deltaTime) {
        for (ObjFlecha flecha : flechas) {
            flecha.setX(flecha.getX() + deltaTime * Constants.FLECHA_VELOC);
            if (flecha.getX() > game.camera.viewportWidth) {
                flechas.removeValue(flecha, true);
            }
        }
    }

    private int arcoPosY;
    private int arcoPrepare;
    private Vector3 antTouchPos;

    private void controleArcoUpdate(float deltaTime) {
        //System.out.println("ArcoPosY:" + arcoPosY);
        //System.out.println("arcoPrepare:" + arcoPrepare);

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            //Logger.getLogger(ControlScreenGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("FPS:" + Gdx.graphics.getFramesPerSecond());

        if (!arco.isAnimationCorpoPlay()) {

            if (Gdx.input.isTouched()) {
                //converte as cordenadas do touch para as da camera
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                game.camera.unproject(touchPos);

                if (touchPos.x > Constants.ARCO_MAX_X) {
                    arcoPrepare = -1;
                }

                if (arcoPrepare >= 0) {
                    switch (arcoPrepare) {
                        case 0:
                            arcoPrepare = 1;
                            antTouchPos = touchPos;
                            if (controlScreenGameGUI.isFlechaZero()) {
                                arco.setAnimationKeyFrameCorpo(ObjArco.KEY_FRAME_ARCO_FLECHA_AUSENTE, false);
                            } else {
                                arco.setAnimationKeyFrameCorpo(ObjArco.KEY_FRAME_ARCO_ARMADO, false);
                            }
                            break;
                        case 1:
                            if (antTouchPos.y > touchPos.y + Constants.ARCO_GRADE || antTouchPos.y < touchPos.y - Constants.ARCO_GRADE) {
                                arcoPrepare = 2;
                            }
                            break;
                        case 2:
                            //arco.setAnimationKeyFrameCorpo(ObjArco.KEY_FRAME_ARCO_POSICAO_INICIAL, true);
                            if (!arco.isAnimationPernasPlay()) {
                                if (antTouchPos.y < touchPos.y) {
                                    arco.setAnimationKeyFramePernas(ObjArco.KEY_FRAME_PERNAS_PASSO, false);
                                    arco.setAnimationStartFramePernas(ObjArco.START_FRAME_PERNAS_PASSO_CIMA);
                                    arcoPosY = (int) (arco.getY() + Constants.ARCO_GRADE);
                                } else {
                                    arco.setAnimationKeyFramePernas(ObjArco.KEY_FRAME_PERNAS_PASSO, true);
                                    arco.setAnimationStartFramePernas(ObjArco.START_FRAME_PERNAS_PASSO_BAIXO);
                                    arcoPosY = (int) (arco.getY() - Constants.ARCO_GRADE);
                                }
                            }
                            arcoPrepare = -1;
                            break;

                        default:
                            break;
                    }

                } else {
                    if (!controlScreenGameGUI.isFlechaZero()) {
                        arco.setAnimationKeyFrameCorpo(ObjArco.KEY_FRAME_ARCO_POSICAO_INICIAL, true);
                    }
                }

            } else {
                if (arcoPrepare > 0) {
                    if (!controlScreenGameGUI.isFlechaZero()) {
                        if (arcoPrepare == 1 && !arco.isAnimationCorpoPlay()) {
                            criaFlecha();
                            if (controlScreenGameGUI.isFlechaZero()) {
                                arco.setAnimationKeyFrameCorpo(ObjArco.KEY_FRAME_ARCO_FLECHA_AUSENTE, false);
                            } else {
                                arco.setAnimationKeyFrameCorpo(ObjArco.KEY_FRAME_ARCO_FLECHA_NOVA, false);
                            }
                        } else {
                            arco.setAnimationKeyFrameCorpo(ObjArco.KEY_FRAME_ARCO_POSICAO_INICIAL, true);
                        }
                    } else {
                        arco.setAnimationKeyFrameCorpo(ObjArco.KEY_FRAME_ARCO_FLECHA_AUSENTE, false);
                    }
                }
                arcoPrepare = 0;
            }
        }

        if (arco.isAnimationStartFramePernas()) {
            if (Math.floor(arco.getY()) != arcoPosY) {
                if (arcoPosY > arco.getY()) {
                    arco.setY(arco.getY() + (Constants.ARCO_VELOC * deltaTime));
                    if (arcoPosY < arco.getY()) {
                        arco.setY(arcoPosY);
                    }
                } else {
                    arco.setY(arco.getY() - (Constants.ARCO_VELOC * deltaTime));
                    if (arcoPosY > arco.getY()) {
                        arco.setY(arcoPosY);
                    }
                }
            } else {
                arco.resetFrameStartPernas();
            }
        }

        //limites
        if (arco.getY() < 0) {
            arco.setY(0);
        } else if (arco.getY() > game.camera.viewportHeight) {
            arco.setY(game.camera.viewportHeight);
        }

        if (controlScreenGameGUI.isVidaZero()) {
            game.setScreen(new ScreenGameOver(game));
        }

        arco.update(deltaTime);

    }

}
