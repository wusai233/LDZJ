package gam.org.com.leidianzhanji.play;

import gam.org.com.leidianzhanji.R;
import gam.org.com.leidianzhanji.npc.BOSS1;
import gam.org.com.leidianzhanji.npc.BOSS2;
import gam.org.com.leidianzhanji.npc.BOSS3;
import gam.org.com.leidianzhanji.npc.BOSS4;
import gam.org.com.leidianzhanji.npc.BOSS5;
import gam.org.com.leidianzhanji.npc.BOSS6;
import gam.org.com.leidianzhanji.npc.NPC;
import gam.org.com.leidianzhanji.npc.NullBOSS;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;

/**
 * 关卡的选择
 */
public class Level {
    private boolean isDownReturn = false;
    private boolean isDownWD = false;
    private boolean isDownLeft = false;
    private boolean isDownRight = false;
    private boolean isDownPlay = false;
    GameDraw gameDraw;
    Bitmap im1, im2;
    Bitmap bt, ms, an, liang;
    // Bitmap guang1, guang2;
    Bitmap wdan, wdliang;
    Bitmap anRight, guangRight;
    Bitmap wenhao;
    Bitmap back_an1, back_an2;

    private int mode, time;// time:次数
    private final int maxTime = 10;// 次数的最大值
    private boolean isBack = false;// 用于判断是否返回

    int alp, av;// alp:透明度

    NPC npc;

    public Level(GameDraw _mc) {
        gameDraw = _mc;
    }

    public void init(Resources res) {
        im1 = BitmapFactory.decodeResource(res, R.drawable.sl_bg1);
        im2 = BitmapFactory.decodeResource(res, R.drawable.sl_bg2);
        wdan = BitmapFactory.decodeResource(res, R.drawable.wd_an1);
        wdliang = BitmapFactory.decodeResource(res, R.drawable.wd_an2);
        an = BitmapFactory.decodeResource(res, R.drawable.cj_an1);
        liang = BitmapFactory.decodeResource(res, R.drawable.cj_an2);
        anRight = BitmapFactory.decodeResource(res, R.drawable.sl_right1);
        guangRight = BitmapFactory.decodeResource(res, R.drawable.sl_right2);
        wenhao = BitmapFactory.decodeResource(res, R.drawable.level_wenhao);
        back_an1 = BitmapFactory.decodeResource(res, R.drawable.back_an1);
        back_an2 = BitmapFactory.decodeResource(res, R.drawable.back_an2);
        initBg(res);
        // guang1 = BitmapFactory.decodeResource(res, R.drawable.sl_guang1);
        // guang2 = BitmapFactory.decodeResource(res, R.drawable.sl_guang2);

        // wdzi = BitmapFactory.decodeResource(res, R.drawable.wd_zi1);

        // anzi = BitmapFactory.decodeResource(res, R.drawable.cj_zi1);
        // bt = BitmapFactory.decodeResource(res, res.getIdentifier("sl_dt"
        // + ((Game.level - 1) % 4 + 1), "drawable",
        // GameDraw.context.getPackageName()));
        // ms = BitmapFactory.decodeResource(res, res.getIdentifier("sl_ms"
        // + Game.level, "drawable", GameDraw.context.getPackageName()));
    }

    public void initBg(Resources res) {
        bt = BitmapFactory.decodeResource(res, res.getIdentifier("sl_dt"
                        + ((Game.level - 1) % 4 + 1), "drawable",
                GameDraw.context.getPackageName()));
        ms = BitmapFactory.decodeResource(res, res.getIdentifier("sl_ms"
                + Game.level, "drawable", GameDraw.context.getPackageName()));

        // if (Game.level > 12) {
        // ms = BitmapFactory.decodeResource(
        // res,
        // res.getIdentifier("sl_ms" + 5, "drawable",
        // GameDraw.context.getPackageName()));
        // }
    }

    public void free() {
        im1 = null;
        im2 = null;
        an = null;
        liang = null;
        bt = null;
        ms = null;
        wdan = null;
        wdliang = null;
        anRight = null;
        guangRight = null;
        wenhao = null;
        // anzi = null;
        // guang1 = null;
        // guang2 = null;

        // wdzi = null;
    }

    public void reset(int _m) {
        mode = _m;
        time = 0;
        GameDraw.gameSound(2);
        gameDraw.canvasIndex = GameDraw.CANVAS_LEVEL;
    }

    public void draw(Canvas g, Paint paint, int time) {
//        g.drawBitmap(im1, 0, -423 + time * (float) 42.3, paint);
//        Tools.paintMImage(g, im1, 240, -423 + time * (float) 42.3, paint);
//        g.drawBitmap(im2, 0, 800 - time * (float) 39.7, paint);
//        Tools.paintMImage(g, im2, 240, 800 - time * (float) 39.7, paint);
//        Game.drawTop(g, paint, time);
//        Game.drawDown(g, paint, time, isDownReturn);
        g.drawBitmap(im1, 960 - im1.getWidth(), 69, paint);
        Tools.paintMImage(g, im1, 960, 69, paint);
        g.drawBitmap(im2, 960 - im2.getWidth(), 553, paint);
        Tools.paintMImage(g, im2, 960, 553, paint);
//        Game.drawTop(g, paint, time);
//        Game.drawDown(g, paint, time, isDownReturn);
        g.drawBitmap(back_an1, back_an1.getWidth() / 2, 873, paint);
        g.drawBitmap(back_an2, back_an2.getWidth() / 2, 873, paint);
        if (isDownPlay)
            g.drawBitmap(liang, 1101, 873, paint);
        else
            g.drawBitmap(an, 1101, 873, paint);
        if (isDownWD)
            g.drawBitmap(wdliang, 619, 873, paint);
        else
            g.drawBitmap(wdan, 619, 873, paint);
        if (isDownRight)
            g.drawBitmap(guangRight, 1175, 645, paint);
        else
            g.drawBitmap(guangRight, 1175, 645, paint);
        if (isDownLeft)
            Tools.paintMImage(g, guangRight, 701, 645, paint);
        else
            Tools.paintMImage(g, guangRight, 701, 645, paint);
    }

    public void render(Canvas g, Paint paint) {
        switch (mode) {
            case 0:
                draw(g, paint, time);
                break;
            case 1:
                initBg(gameDraw.res);
                draw(g, paint, 10);
                paint.setAlpha((time - 5) * 50 + 5);
                g.drawBitmap(bt, 960 - bt.getWidth() / 2, 83, paint);
                g.drawBitmap(ms, 960 - ms.getWidth() / 2, 631, paint);
                paint.setAlpha(255);
                break;
            case 2:
                draw(g, paint, 10);
                g.drawBitmap(bt, 960 - bt.getWidth() / 2, 83, paint);
                g.drawBitmap(ms, 960 - ms.getWidth() / 2, 631, paint);
                npc.render(g, paint);
                paint.setAlpha(alp);
                renderGuang(g, paint);
                break;
            case 3:
                if (!isBack) {
                    gameDraw.game.render(g, paint);
                } else {
                    g.drawBitmap(Menu.bg, 0, 0, paint);
                }
                draw(g, paint, time);
                break;
            case 10:
                draw(g, paint, time);
                break;
        }
    }

    public void renderGuang(Canvas g, Paint paint) {
        g.drawBitmap(anRight, 1151, 622, paint);
        Tools.paintMImage(g, anRight, 676, 622, paint);
    }

    public void upData() {
        switch (mode) {
            case 0:
                time++;
                if (time >= maxTime) {
                    time = 8;
                    gameDraw.chooseAirplane.free();
                    gameDraw.menu.free();
                    mode = 1;
                    isBack = false;
                }
                break;
            case 1:
                time++;
                if (time >= maxTime) {
                    time = 0;
                    mode = 2;
                    alp = 255;
                    av = -30;
                    gameDraw.game.init(gameDraw.res);
                } else if (time == 9) {
                    gameDraw.game.npcManager.init(gameDraw.res);
                    Game.cx = 0;
                    Game.mx = 0;
                    if (Game.level == Data.level && !MainActivity.isEndPlay) {
                        npc = new NullBOSS(wenhao, 877, 141, 0);
                        break;
                    }
                    switch (Game.level) {
                        case 1:
                            npc = new BOSS1(gameDraw.game.npcManager.im[1], 960, 245,
                                    101);
                            break;
                        case 2:
                            npc = new BOSS2(gameDraw.game.npcManager.im[2], 960, 240,
                                    102);
                            break;
                        case 3:
                            npc = new BOSS4(
                                    Tools.getScale(gameDraw.game.npcManager.im[4]),
                                    960, 245, 103);
                            break;
                        case 4:
                            npc = new BOSS6(
                                    Tools.getScale(gameDraw.game.npcManager.im[6]),
                                    960, 265, 104);
                            break;
                        case 5:
                            npc = new BOSS5(gameDraw.game.npcManager.im[5], 960, 250,
                                    105);
                            break;
                        case 6:
                            npc = new BOSS3(gameDraw.game.npcManager.im[3], 960, 250,
                                    106);
                            break;

                        case 7:
                            npc = new BOSS1(gameDraw.game.npcManager.im[7], 960, 245,
                                    107);
                            break;
                        case 8:
                            npc = new BOSS2(gameDraw.game.npcManager.im[8], 960, 240,
                                    108);
                            break;
                        case 9:
                            npc = new BOSS4(
                                    Tools.getScale(gameDraw.game.npcManager.im[10]),
                                    960, 245, 109);
                            break;
                        case 10:
                            npc = new BOSS6(
                                    Tools.getScale(gameDraw.game.npcManager.im[12]),
                                    960, 265, 110);
                            break;
                        case 11:
                            npc = new BOSS5(gameDraw.game.npcManager.im[11], 960, 250,
                                    111);
                            break;
                        case 12:
                            npc = new BOSS3(gameDraw.game.npcManager.im[9], 960, 250,
                                    112);
                            break;
                        // case 13:
                        // npc = new BOSS1(gameDraw.game.npcManager.im[1], 240, 245,
                        // 113);
                        // break;
                        // case 14:
                        // npc = new BOSS2(gameDraw.game.npcManager.im[2], 240, 240,
                        // 114);
                        // break;
                    }
                }
                break;
            case 2:
                alp += av;
                if (alp < 100) {
                    alp = 100;
                    av = Math.abs(av);
                } else if (alp > 255) {
                    alp = 255;
                    av = -Math.abs(av);
                }
                if (time > 0) {
                    time--;
                    if (time <= 0) {
                        gameDraw.game.reset();
                        gameDraw.canvasIndex = GameDraw.CANVAS_LEVEL;
                        mode = 3;
                        time = 10;
                        GameDraw.gameSound(2);
                    }
                }
                break;
            case 3:
                time--;
                if (isBack) {
                    if (time <= 0) {
                        gameDraw.chooseAirplane.init(gameDraw.res);
                        gameDraw.chooseAirplane.reset();
                        mode = 0;
                    }
                } else {
                    if (time <= 0) {
                        gameDraw.canvasIndex = GameDraw.CANVAS_GAME;
                    } else if (time == 5) {
                        GameDraw.isPlayMusic(GameDraw.menuMediaPlayer,
                                GameDraw.bossMediaPlayer, GameDraw.gameMediaPlayer);
                    }
                }
                break;
            case 10:
                time++;
                if (time >= maxTime) {
                    time = 0;
                    gameDraw.game.free();
                    mode = 1;
                }
                break;
        }
    }

    public void touchDown(float tx, float ty) {
        if (mode == 2 && time == 0) {
            if (ty > 873 && ty < 967 && tx > 1101 && tx < 1298) {// 开始
                isDownPlay = true;
                GameDraw.gameSound(1);
            } else if (ty > 873 && ty < 967 && tx > 619 && tx < 816) {// 无敌
                isDownWD = true;
                GameDraw.gameSound(1);
            } else if (ty > 654 && ty < 747 && tx > 701 && tx < 753) {// 上一关
                GameDraw.gameSound(1);
                isDownLeft = true;
            } else if (ty > 654 && ty < 747 && tx > 1175 && tx < 1227) {// 下一关
                GameDraw.gameSound(1);
                isDownRight = true;
            } else if (ty > 730 && ty < 967 && tx > 849 && tx < 1067) {// 返回
                GameDraw.gameSound(1);
                isDownReturn = true;
            }
        }
    }

    public void touchUp(float tx, float ty) {
        if (mode == 2 && time == 0) {
            if ((ty > 873 && ty < 967 && tx > 1101 && tx < 1298) && isDownPlay) {// 开始
                isDownPlay = false;
                time = 3;
            } else if ((ty > 873 && ty < 967 && tx > 619 && tx < 816)
                    && isDownWD) {// 无敌
                isDownWD = false;
//				PaymentJoy.getInstance(this).startCharge(new PaymentParam(8));
            } else if ((ty > 654 && ty < 747 && tx > 701 && tx < 753)
                    && isDownLeft) {// 上一关
                isDownLeft = false;
                if (Game.level != 1) {
                    Game.level--;
                    mode = 1;
                    time = 8;
                }
            } else if ((ty > 654 && ty < 747 && tx > 1175 && tx < 1227)
                    && isDownRight) {// 下一关
                isDownRight = false;
                if (Game.level < Data.level) {
                    Game.level++;
                    mode = 1;
                    time = 8;
                }
            } else if ((ty > 730 && ty < 967 && tx > 849 && tx < 1067) && isDownReturn) {// 返回
                isDownReturn = false;
                isBack = true;
                mode = 3;
                time = maxTime;
            }
        }

    }

    public void touchMove(float tx, float ty) {
        if (mode == 2 && time == 0) {
            if (!(ty > 873 && ty < 967 && tx > 1101 && tx < 1298) && isDownPlay) {// 开始
                isDownPlay = false;
            } else if (!(ty > 873 && ty < 967 && tx > 619 && tx < 816)
                    && isDownWD) {// 无敌
                isDownWD = false;
            } else if (!(ty > 654 && ty < 747 && tx > 701 && tx < 753)
                    && isDownLeft) {// 上一关
                isDownLeft = false;
            } else if (!(ty > 654 && ty < 747 && tx > 1175 && tx < 1227)
                    && isDownRight) {// 下一关
                isDownRight = false;
            } else if (!(ty > 730 && ty < 967 && tx > 849 && tx < 1067) && isDownReturn) {// 返回
                isDownReturn = false;
            }
        }

    }

    public void keyDown(int k) {
        switch (k) {
            case KeyEvent.KEYCODE_DPAD_UP://向上
                Log.e("jamie", "－－－－－向上－－－－－");
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN://向下
                Log.e("jamie", "－－－－－向下－－－－－");
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT://向左
                Log.e("jamie", "－－－－－向左－－－－－");
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT://向右
                Log.e("jamie", "－－－－－向右－－－－－");
                break;
            case KeyEvent.KEYCODE_ENTER://确定
                Log.e("jamie", "－－－－－确定－－－－－");
                break;
            case KeyEvent.KEYCODE_BACK://返回
                Log.e("jamie", "－－－－－返回－－－－－");
                break;
            case KeyEvent.KEYCODE_HOME://房子
                Log.e("jamie", "－－－－－房子－－－－－");
                break;
            case KeyEvent.KEYCODE_MENU://菜单
                Log.e("jamie", "－－－－－菜单－－－－－");
                break;
        }
    }

//	public void PaymentResult(int resultCode, String[] cbParam) {
//		if (PaymentResultCode.PAYMENT_SUCCESS == resultCode) {
//			Game.isWD = true;
//			time = 3;
//		}
//	}
}
