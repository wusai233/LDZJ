package gam.org.com.leidianzhanji.play;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.KeyEvent;

import gam.org.com.leidianzhanji.R;

/**
 * 中间的一些计费弹框
 */
public class BillingDialog {
    GameDraw gameDraw;
    Bitmap im;
    Bitmap an1, an2, an4;
    Bitmap tu;
    Bitmap zi;
    int mode, t;
    int id, to;

    Bitmap bs_huan;
    /**
     * 按钮类型 0：战机1   1：战机1  2：战机1   3：战机1   4：返回   5：出击
     */
    int keyType = 0;
    int flag = 0;

    public BillingDialog(GameDraw _mc) {
        gameDraw = _mc;
    }

    public void init(Resources res) {
        if (id != 20 && id != 30 && id != 40) {
            im = BitmapFactory.decodeResource(res, R.drawable.gou_im);
        } else {
            im = BitmapFactory.decodeResource(res, R.drawable.mr_im);
        }
        an2 = BitmapFactory.decodeResource(res, R.drawable.gou_back);
        an1 = BitmapFactory.decodeResource(res, R.drawable.mr_an1);
        // an3 = BitmapFactory.decodeResource(res, R.drawable.gou_an3);
        switch (id) {
            case 1:// 护盾补给
                zi = BitmapFactory.decodeResource(res, R.drawable.gou_zi1);
                break;
            case 2:// 必杀补给
                zi = BitmapFactory.decodeResource(res, R.drawable.gou_zi3);
                break;
            case 10:// 立即复活
                zi = BitmapFactory.decodeResource(res, R.drawable.gou_zi2);
                // wdan = BitmapFactory.decodeResource(res, R.drawable.wd_an1);
                break;
            case 30:// 开启擎天柱
                zi = BitmapFactory.decodeResource(res, R.drawable.gou_zi5);
                break;
            case 40:// 超值礼包
                zi = BitmapFactory.decodeResource(res, R.drawable.gou_zi6);
                break;
        }

        bs_huan = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.bs_huan_im);
    }


    int bs_huan_t = 0;

    /**
     * 选择圈圈的绘制
     */
    public void renderAN(Canvas g, boolean huan, Paint paint) {
        if (huan) {
            switch (keyType) {
                case 0:
                    g.drawBitmap(bs_huan, null, new RectF(1085+84/2 - (bs_huan_t * 10 + 40), 255+23/2 - (bs_huan_t * 10 + 40), 1085+84/2 + (bs_huan_t * 10 + 40),  255+23/2 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 1:
                    g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 840+95/2 - (bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 840+95/2 + (bs_huan_t * 10 + 40)), paint);
                    break;
            }
            bs_huan_t--;
            if (bs_huan_t < 0)
                bs_huan_t = 10;
        }
    }

    public void free() {
        im = null;
        an1 = null;
        an2 = null;
        // an3 = null;
        an4 = null;
        zi = null;
        // wdan = null;
    }

    public void reset(int _id, int _to) {
        id = _id;
        to = _to;
        mode = 0;
        t = 0;
        GameDraw.isShake = false;
        gameDraw.canvasIndex = GameDraw.CANVAS_BILLING_DIALOG;
    }

    public void render(Canvas g, Paint paint) {
        if (mode != 31) {
            gameDraw.paint(g, to);
        }
        switch (mode) {
            case 0:
            case 21:
                g.drawColor((t * 30) << 24);
                break;
            case 1:
            case 20:
                g.drawColor(120 << 24);
                paint.setAlpha(t * 50 + 5);
                renderJM(g, paint);
                paint.setAlpha(255);
                break;
            case 2:
            case 3:
                g.drawColor(120 << 24);
                renderJM(g, paint);
                break;
            case 29:
                // g.drawColor(120 << 24);
                // g.drawBitmap(im, 35, 200, paint);
                // Tools.paintMImage(g, im, 240, 200, paint);

                // g.drawBitmap(wdan, 150, 270, paint);
                // Tools.paintMImage(g, wdan, 240, 270, paint);
                // g.drawBitmap(wdzi1, 155, 287, paint);
                // g.drawBitmap(wdan, 124, 400, paint);
                // Tools.paintMImage(g, wdan, 240, 400, paint);
                break;
            case 30:
                g.drawColor(120 << 24);
                renderJM(g, paint);
                g.drawColor((t * 25 + 5) << 24);
                break;
            case 31:
            case 32:
                g.drawColor(0xff000000);
                paint.setAlpha(t * 25 + 5);
                g.drawBitmap(Menu.bg, 0, 0, paint);
                paint.setAlpha(255);
                break;
        }
    }

    public void renderJM(Canvas g, Paint paint) {
        if (MainActivity.isShowBuyMessage == false && id == 10 && mode != 29) {
            return;
        }

        if (id != 20 && id != 30 && id != 40) {
            g.drawBitmap(im, 960 - im.getWidth(), 200, paint);
            Tools.paintMImage(g, im, 960, 200, paint);
        }

        switch (id) {
            case 1:
            case 2:
            case 10:
                g.drawBitmap(zi, 960 - zi.getWidth() / 2, 320, paint);
                break;
            case 30:
                g.drawBitmap(im, 664, 150, paint);
                Tools.paintMImage(g, im, 960, 150, paint);
                Tools.paintM2Image(g, im, 664, 550, paint);
                Tools.paintRotateImage(g, im, 960, 550, 180, 302, 458, paint);

                g.drawBitmap(zi, 960 - zi.getWidth() / 2, 320, paint);

                g.drawBitmap(an2, 1085, 233, paint);
                g.drawBitmap(an1, 960 - an1.getWidth() / 2, 840, paint);
                flag = 0;
                renderAN(g, true, paint);
                break;
            case 40:
                g.drawBitmap(im, 664, 170, paint);
                Tools.paintMImage(g, im, 960, 170, paint);
                Tools.paintM2Image(g, im, 664, 550, paint);
                Tools.paintRotateImage(g, im, 960, 550, 180, 302, 458, paint);

                g.drawBitmap(zi, 960 - zi.getWidth() / 2, 310, paint);

                g.drawBitmap(an2, 1085, 233, paint);
                g.drawBitmap(an1, 960 - an1.getWidth() / 2, 840, paint);
                flag = 1;
                renderAN(g, true, paint);
                break;
        }

        if (id != 20 && id != 30 && id != 40) {
            g.drawBitmap(an2, 1085, 233, paint);
            g.drawBitmap(an1, 960 - an1.getWidth() / 2, 700, paint);
        }
    }

    public void upData() {
        switch (mode) {
            case 0:
                t++;
                if (t >= 4) {
                    t = 0;
                    mode = 1;
                } else if (t == 2) {
                    init(gameDraw.res);
                }
                break;
            case 1:
                t++;
                if (t >= 5) {
                    if (MainActivity.isShowBuyMessage) {
                        t = 0;
                        mode = 2;
                    } else if (id == 10) {
                        t = 0;
                        mode = 3;
                    }
                }
                break;
            case 20:
                t--;
                if (t <= 0) {
                    free();
                    t = 4;
                    mode = 21;
                    Data.chackBH();
                    Data.save();
                }
                break;
            case 21:
                t--;
                if (t <= 0) {
                    gameDraw.canvasIndex = (byte) to;
                    if (to == 20 && Game.isFang == true && id == 2) {
                        gameDraw.game.touchDown(100, 750);
                    }
                }
                break;
            case 30:
                t++;
                if (t >= 10) {
                    if (id == 10) {
                        t = 0;
                        mode = 31;
                    } else if (id == 20) {
                        t = 0;
                        mode = 31;
                    }
                }
                break;
            case 31:
                t++;
                gameDraw.menu.initPart(gameDraw.res);
                gameDraw.game.free();
                free();
                if (t >= 10) {
                    Menu.index = Menu.NULL;
                    GameDraw.isPlayMusic(GameDraw.gameMediaPlayer,
                            GameDraw.bossMediaPlayer, GameDraw.menuMediaPlayer);
                    gameDraw.menu.reset2();
                }
                break;
            case 32:
                t++;
                if (t >= 10) {
                    Menu.index = Menu.NULL;
                    gameDraw.menu.reset2();
                } else if (t == 3) {
                    gameDraw.chooseAirplane.free();
                    free();
                } else if (t == 5) {
                    if (MainActivity.gcDebug) {
                        System.gc();
                    }
                } else if (t == 7) {
                    gameDraw.menu.initPart(gameDraw.res);
                }
                break;
        }
    }

    public void touchDown(float tx, float ty) {
        switch (mode) {
            case 2:
                if (id == 30) {
                    if (tx > 863 && tx < 1068 && ty > 837 && ty < 932) {
                        Log.e("touchDown", ".....1");
                        GameDraw.gameSound(1);
                        gameDraw.chooseAirplane.buyID = 3;
                        t = 5;
                        mode = 20;
                    } else if (tx > 1085 && tx < 1085 + 84 && ty > 233 && ty < 233 + 76) {
                        Log.e("touchDown", ".....2");
                        GameDraw.gameSound(1);
                        t = 5;
                        mode = 20;
                    }
                } else if (id == 40) {
                    if (tx > 863 && tx < 1068 && ty > 837 && ty < 932) {
                        Log.e("touchDown", ".....3");
                        GameDraw.gameSound(1);
                    } else if (tx > 1085 && tx < 1085 + 84 && ty > 233 && ty < 233 + 76) {
                        Log.e("touchDown", ".....4");
                        GameDraw.gameSound(1);
                        t = 5;
                        mode = 20;
                    }
                } else if (id != 20) {
                    if (tx > 863 && tx < 1068 && ty > 837 && ty < 932) {
                        Log.e("touchDown", ".....5");
                        switch (id) {
                            case 1:
                                GameDraw.gameSound(1);
                                mode = 3;
                                break;
                            case 2:
                                GameDraw.gameSound(1);
                                mode = 3;
                                break;
                            case 10:
                                GameDraw.gameSound(1);
                                mode = 3;
                                break;
                        }
                    } else if (tx > 1085 && tx < 1085 + 84 && ty > 233 && ty < 233 + 76) {
                        Log.e("touchDown", ".....6");
                        switch (id) {
                            case 1:
                            case 2:
                                t = 5;
                                mode = 20;
                                Game.isFang = false;
                                GameDraw.gameSound(1);
                                break;
                            case 10:
                                // if (Game.level <= GameWin.MAX_LEVEL) {
                                // GameDraw.gameSound(1);
                                // t = 0;
                                // mode = 29;
                                // } else {
                                // }
                                GameDraw.gameSound(1);
                                t = 0;
                                mode = 30;
                                break;
                        }
                    }
                } else {
                    if (tx > 863 && tx < 1068 && ty > 837 && ty < 932) {
                        Log.e("touchDown", ".....7");
                        mode = 3;
                        GameDraw.gameSound(1);
                    } else if (tx > 1085 && tx < 1085 + 84 && ty > 233 && ty < 233 + 76) {
                        Log.e("touchDown", ".....8");
                        GameDraw.gameSound(1);
                        t = 0;
                        mode = 30;
                    }
                }
                break;
            case 29:
                if (tx > 100 && tx < 380 && ty > 200 && ty < 380) {
                    Log.e("touchDown", ".....9");
                    GameDraw.gameSound(1);
                } else if (tx > 100 && tx < 380 && ty > 380 && ty < 560) {
                    Log.e("touchDown", ".....10");
                    GameDraw.gameSound(1);
                    t = 0;
                    mode = 30;
                }
                break;
        }
    }

    public void keyDown(int k) {
        switch (k) {
            case KeyEvent.KEYCODE_DPAD_UP://向上
                Log.e("jamie", "－－－－－向上－－－－－");
                GameDraw.gameSound(1);
                switch (keyType){
                    case 0:
                        keyType=1;
                        break;
                    case 1:
                        keyType=0;
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN://向下
                Log.e("jamie", "－－－－－向下－－－－－");
                GameDraw.gameSound(1);
                //
                switch (keyType){
                    case 0:
                        keyType=1;
                        break;
                    case 1:
                        keyType=0;
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT://向左
                Log.e("jamie", "－－－－－向左－－－－－");
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT://向右
                Log.e("jamie", "－－－－－向右－－－－－");
                break;
            case KeyEvent.KEYCODE_ENTER://确定
                Log.e("jamie", "－－－－－确定－－－－－");
                GameDraw.gameSound(1);
                switch (keyType){
                    case 0:
                        keyType=1;
                        break;
                    case 1:
//                        keyType=0;
                        break;
                }
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
//			if (cbParam[0].equals("7")) {// 购买保护
//				Game.baohu += 5;
//				t = 5;
//				mode = 20;
//				Data.save();
//			} else if (cbParam[0].equals("6")) {// 购买必杀
//				Game.bisha += 5;
//				t = 5;
//				mode = 20;
//				Data.save();
//			} else if (cbParam[0].equals("10")) {// 购买生命
//				Game.sm = 3;
//				gameDraw.game.airplane.createPlayer();
//				t = 5;
//				mode = 20;
//				Airplane.fh = true;
//			} else if (cbParam[0].equals("1")) {
//				Data.buy = true;
//				gameDraw.game.addShuijing(1000);
//				Game.bisha += 1;
//				Data.bh += 1;
//				t = 5;
//				mode = 20;
//				Data.save();
//			} else if (cbParam[0].equals("8")) {
//				Game.isWD = true;
//				Game.sm = 3;
//				gameDraw.game.airplane.createPlayer();
//				gameDraw.game.reset();
//				t = 5;
//				mode = 20;
//				gameDraw.canvasIndex = GameDraw.CANVAS_BILLING_DIALOG;
//			} else if (cbParam[0].equals("12")) {
//				Game.bisha += 5;
//				Game.baohu += 5;
//				gameDraw.game.addShuijing(5000);
//				t = 5;
//				mode = 20;
//				Data.save();
//			}
//		} else {
//			if (cbParam[0].equals("10")
//					&& MainActivity.isShowBuyMessage == false) {
//				if (Game.level <= GameWin.MAX_LEVEL) {
//					GameDraw.gameSound(1);
//					t = 0;
//					mode = 29;
//				} else {
//					GameDraw.gameSound(1);
//					t = 0;
//					mode = 30;
//				}
//			} else {
//				Game.isFang = false;
//				mode = 2;
//			}
//		}
//	}
}
