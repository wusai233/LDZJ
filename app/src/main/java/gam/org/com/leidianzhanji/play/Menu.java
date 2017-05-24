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

public class Menu {
    private String TGA = "Menu";
    public static int index;
    public static final int NULL = 0;
    public static final int EXIT_GAME = 1;
    public static final int SETTING = 2;
    public static final int ACHIEVE = 3;
    public static final int PLAYGAME = 4;
    public static final int UPGRADE = 5;
    public static final int BOSS = 6;
    public static final int HELP = 7;
    public static boolean[] s = new boolean[]{false, false};
    private boolean isDownStart = false;
    private boolean isDownAchieve = false;
    private boolean isDownSetting = false;
    private boolean isDownBoss = false;
    private boolean isDownUpgrade = false;
    private boolean isDownHelp = false;
    private boolean isDownExit = false;
    GameDraw gameDraw;
    int mode, time, id, time2;

    /**
     * 按钮类型 0：挑战BOSS   1：战斗升级  2：帮助   3：开始   4：成就   5：设置
     */
    int keyType = 3;


    public static Bitmap bg;
    Bitmap an2;
    Bitmap anSetting, anAchieve, liangSetting, liangAchieve, anStart,
            liangStart;
    Bitmap gai1, gai2;
    Bitmap fei2;
    Bitmap suo;
    Bitmap bt1;
    Bitmap[] an_a = new Bitmap[3];
    Bitmap[] an_l = new Bitmap[3];
    Bitmap bs_huan;
    public static byte isLevelOrBoss = 0;
    int alp, av;

    public Menu(GameDraw _mc) {
        gameDraw = _mc;
        bg = BitmapFactory.decodeResource(gameDraw.res, R.drawable.menu_bg1);
    }

    public void init(Resources res) {
        Log.i("Menu", "----------init----------------");
        an2 = BitmapFactory.decodeResource(res, R.drawable.menu_an2);
        anSetting = BitmapFactory.decodeResource(res, R.drawable.menu_an31);
        liangSetting = BitmapFactory.decodeResource(res, R.drawable.menu_an32);
        anAchieve = BitmapFactory.decodeResource(res, R.drawable.menu_an241);
        liangAchieve = BitmapFactory.decodeResource(res, R.drawable.menu_an242);
        anStart = BitmapFactory.decodeResource(res, R.drawable.menu_an1_1);
        liangStart = BitmapFactory.decodeResource(res, R.drawable.menu_an52);
//        gai1 = BitmapFactory.decodeResource(res, R.drawable.menu_gai1);
        gai2 = BitmapFactory.decodeResource(res, R.drawable.menu_gai2);
        fei2 = BitmapFactory.decodeResource(res, R.drawable.menu_fei1);
        bt1 = BitmapFactory.decodeResource(res, R.drawable.menu_bt1);
        suo = BitmapFactory.decodeResource(res, R.drawable.menu_suo);
        an_a[0] = BitmapFactory.decodeResource(res, R.drawable.menu_an61);
        an_a[1] = BitmapFactory.decodeResource(res, R.drawable.menu_an71);
        an_a[2] = BitmapFactory.decodeResource(res, R.drawable.menu_an81);
        an_l[0] = BitmapFactory.decodeResource(res, R.drawable.menu_an62);
        an_l[1] = BitmapFactory.decodeResource(res, R.drawable.menu_an72);
        an_l[2] = BitmapFactory.decodeResource(res, R.drawable.menu_an82);
        bs_huan = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.bs_huan_im);
    }

    public void initPart(Resources res) {
        Log.i("Menu", "----------initPart----------------");
        an2 = BitmapFactory.decodeResource(res, R.drawable.menu_an2);
        anSetting = BitmapFactory.decodeResource(res, R.drawable.menu_an31);
        liangSetting = BitmapFactory.decodeResource(res, R.drawable.menu_an32);
        anAchieve = BitmapFactory.decodeResource(res, R.drawable.menu_an241);
        liangAchieve = BitmapFactory.decodeResource(res, R.drawable.menu_an242);
        anStart = BitmapFactory.decodeResource(res, R.drawable.menu_an1_1);
        liangStart = BitmapFactory.decodeResource(res, R.drawable.menu_an52);
//        gai1 = BitmapFactory.decodeResource(res, R.drawable.menu_gai1);
        gai2 = BitmapFactory.decodeResource(res, R.drawable.menu_gai2);
        bt1 = BitmapFactory.decodeResource(res, R.drawable.menu_bt1);
        suo = BitmapFactory.decodeResource(res, R.drawable.menu_suo);
        an_a[0] = BitmapFactory.decodeResource(res, R.drawable.menu_an61);
        an_a[1] = BitmapFactory.decodeResource(res, R.drawable.menu_an71);
        an_a[2] = BitmapFactory.decodeResource(res, R.drawable.menu_an81);
        an_l[0] = BitmapFactory.decodeResource(res, R.drawable.menu_an62);
        an_l[1] = BitmapFactory.decodeResource(res, R.drawable.menu_an72);
        an_l[2] = BitmapFactory.decodeResource(res, R.drawable.menu_an82);
    }

    int bs_huan_t = 0;

    /**
     * 选择光圈的绘制
     */
    public void renderAN(Canvas g, boolean huan, Paint paint) {
        if (huan) {
            switch (keyType) {
                case 0://挑战Bss
                    g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 425 - (bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 425 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 1://战斗升级
                    g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 573 - (bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 573 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 2: //帮助关于
                    g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 718 - (bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 718 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 3: //开始按钮
                    g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 968 - (bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 968 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 4:  //成就
                    g.drawBitmap(bs_huan, null, new RectF(566 - (bs_huan_t * 10 + 40), 999 - (bs_huan_t * 10 + 40), 579 + (bs_huan_t * 10 + 40), 999 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 5: //设置
                    g.drawBitmap(bs_huan, null, new RectF(1349 - (bs_huan_t * 10 + 40), 999 - (bs_huan_t * 10 + 40), 1349 + (bs_huan_t * 10 + 40), 999 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 6:
                    g.drawBitmap(bs_huan, null, new RectF(1080 + -(bs_huan_t * 10 + 40), 310 + -(bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 425 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 7:
                    g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 573 - (bs_huan_t * 10 + 40), 475 + (bs_huan_t * 10 + 40), 573 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 8:
                    g.drawBitmap(bs_huan, null, new RectF(1080 - (bs_huan_t * 10 + 40), 310 - (bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 425 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 9:
                    g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 573 - (bs_huan_t * 10 + 40), 475 + (bs_huan_t * 10 + 40), 573 + (bs_huan_t * 10 + 40)), paint);
                    break;
            }
            bs_huan_t--;
            if (bs_huan_t < 0)
                bs_huan_t = 10;
        }
    }

    public void free() {
        Log.i("Menu", "----------free----------------");
        an2 = null;
        anSetting = null;
        liangSetting = null;
        anAchieve = null;
        liangAchieve = null;
        anStart = null;
        liangStart = null;
        gai2 = null;
        fei2 = null;
        bt1 = null;
        suo = null;
        for (int i = 0; i < an_a.length; i++) {
            an_a[i] = null;
            an_l[i] = null;
        }
    }

    public void freePart() {
        Log.i("Menu", "----------freePart----------------");
        fei2 = null;
    }

    public void reset() {
        Log.i("Menu", "----------reset----------------");
        mode = 0;
        time = 8;
        time2 = 10;
        gameDraw.airplaneUpgrade.resetData();
        alp = 255;
        av = 15;
        gameDraw.canvasIndex = GameDraw.CANVAS_MENU;
    }

    public void reset2() {
        Log.i("Menu", "----------reset2----------------");
        mode = 6;
        time = 10;
        time2 = 10;

        GameDraw.gameSound(2);
        alp = 255;
        av = 15;

        gameDraw.canvasIndex = GameDraw.CANVAS_MENU;
    }

    public void render(Canvas g, Paint paint) {

        Log.i("Menu", "----------render----------------" + mode);
        switch (mode) {
            case 0:// 静态
                g.drawBitmap(bg, 0, 0, paint);
                g.drawBitmap(an2, 960 - an2.getWidth(), 855, paint);
                Tools.paintMImage(g, an2, 960, 855, paint);
                g.drawBitmap(gai2, 673, 0, paint);
                Tools.paintMImage(g, gai2, 969, 0, paint);
                g.drawBitmap(fei2, 845, 399, paint);
                g.drawBitmap(bt1, 760, 98, paint);
                g.drawBitmap(anStart, 960 - anStart.getWidth() / 2, 870, paint);
                g.drawBitmap(anAchieve, 500, 920, paint);
                g.drawBitmap(anSetting, 1270, 920, paint);
                break;
            case 1:// 上拉、下滑
                Log.e(TGA, "-----------------下拉上滑---- mode = 1-------------");
                g.drawBitmap(bg, 0, 0, paint);
                g.drawBitmap(gai2, 673, 0, paint);
                Tools.paintMImage(g, gai2, 969, 0, paint);
                g.drawBitmap(fei2, 845, 399, paint);
                g.drawBitmap(bt1, 760, 98, paint);
                g.drawBitmap(an2, 960 - an2.getWidth(), 855 + time * 19, paint);
                Tools.paintMImage(g, an2, 960, 855 + time * 19, paint);
                g.drawBitmap(anStart, 960 - anStart.getWidth() / 2, 870 + time * 19, paint);
                g.drawBitmap(anAchieve, 500, 920 + time * 19, paint);
                g.drawBitmap(anSetting, 1270, 920 + time * 19, paint);
                break;
            case 2:// 下拉，上滑
                Log.e(TGA, "-----------------下拉上滑---- mode = 2-------------");
                g.drawBitmap(bg, 0, 0, paint);
                g.drawBitmap(fei2, 845, 399, paint);
                g.drawBitmap(gai2, 673, -100 + time * 10, paint);
                Tools.paintMImage(g, gai2, 969, -100 + time * 10, paint);
                g.drawBitmap(bt1, 760, 0 + time * 10, paint);

                g.drawBitmap(an2, 960 - an2.getWidth(), 855 + time2 * 22, paint);
                Tools.paintMImage(g, an2, 960, 855 + time2 * 22, paint);
                g.drawBitmap(anStart, 845, 870 + time2 * 22, paint);
                g.drawBitmap(anAchieve, 500, 920 + time2 * 22, paint);
                g.drawBitmap(anSetting, 1270, 920 + time2 * 22, paint);
                break;
            case 3:// 飞行
                g.drawBitmap(bg, 0, 0, paint);
                g.drawBitmap(gai2, 673, 0, paint);
                Tools.paintMImage(g, gai2, 969, 0, paint);
                g.drawBitmap(bt1, 760, 98, paint);
                g.drawBitmap(fei2, 845 - time * 120, 399 - time * 100, paint);
                g.drawBitmap(an2, 960 - an2.getWidth(), 855, paint);
                Tools.paintMImage(g, an2, 960, 855, paint);
                g.drawBitmap(anStart, 860, 870, paint);
                g.drawBitmap(anAchieve, 490, 920, paint);
                g.drawBitmap(anSetting, 1270, 920, paint);
                break;
            case 4:// 文字
                freePart();
                g.drawBitmap(bg, 0, 0, paint);
                g.drawBitmap(gai2, 673, 0, paint);
                Tools.paintMImage(g, gai2, 969, 0, paint);
                g.drawBitmap(bt1, 760, 98, paint);
                g.drawBitmap(an2, 960 - an2.getWidth(), 855, paint);
                Tools.paintMImage(g, an2, 960, 855, paint);
                g.drawBitmap(anStart, 860, 870, paint);
                g.drawBitmap(anAchieve, 490, 920, paint);
                g.drawBitmap(anSetting, 1270, 920, paint);
                paint.setAlpha(time * 25);
                for (int i = 0; i < 3; i++) {
                    g.drawBitmap(an_a[i], 683, 370 + i * 150, paint);
                    if (i < 2) {
                        if (s[i] == false) {
                            g.drawBitmap(suo, 743, 389 + i * 150, paint);
                        }
                    }
                }
                paint.setAlpha(255);
                break;
            case 5:// 静态
                g.drawBitmap(bg, 0, 0, paint);
                g.drawBitmap(gai2, 673, 0, paint);
                Tools.paintMImage(g, gai2, 969, 0, paint);
                g.drawBitmap(bt1, 760, 98, paint);
                g.drawBitmap(an2, 960 - an2.getWidth(), 855, paint);
                Tools.paintMImage(g, an2, 960, 855, paint);
                if (isDownStart)
                    g.drawBitmap(liangStart, 860, 870, paint);
                else
                    g.drawBitmap(anStart, 860, 870, paint);
                if (isDownAchieve)
                    g.drawBitmap(liangAchieve, 490, 920, paint);
                else
                    g.drawBitmap(anAchieve, 490, 920, paint);
                if (isDownSetting)
                    g.drawBitmap(liangSetting, 1270, 920, paint);
                else
                    g.drawBitmap(anSetting, 1270, 920, paint);
                if (isDownBoss && s[0])
                    g.drawBitmap(an_l[0], 683, 370, paint);
                else
                    g.drawBitmap(an_a[0], 683, 370, paint);
                if (isDownUpgrade && s[1])
                    g.drawBitmap(an_l[1], 683, 370 + 150, paint);
                else
                    g.drawBitmap(an_a[1], 683, 370 + 150, paint);
                if (isDownHelp)
                    g.drawBitmap(an_l[2], 683, 370 + 300, paint);
                else
                    g.drawBitmap(an_a[2], 683, 370 + 300, paint);
                for (int i = 0; i < 2; i++) {
                    if (!s[i])
                        g.drawBitmap(suo, 743, 389 + i * 150, paint);
                }
                break;
            case 6:// 状态的切换
                if (time >= 0) {
                    g.drawBitmap(bg, 0, 0, paint);
                    g.drawBitmap(gai2, 673, -time * 50, paint);
                    Tools.paintMImage(g, gai2, 969, -time * 50, paint);
                    g.drawBitmap(bt1, 760, 98 - time * 50, paint);

                    g.drawBitmap(an2, 960 - an2.getWidth(), 855 + time * 32, paint);
                    Tools.paintMImage(g, an2, 960, 855 + time * 32, paint);
                    g.drawBitmap(anStart, 860, 870 + time * 32, paint);
                    g.drawBitmap(anAchieve, 490, 920 + time * 32, paint);
                    g.drawBitmap(anSetting, 1270, 920 + time * 32, paint);

                }
                break;
        }
        renderAN(g, true, paint);
    }

    public void renderBT(Canvas g, int time, Paint paint) {
        Log.i("Menu", "----------renderBT----------------");
        g.drawBitmap(gai2, 40, -19 - time * 30, paint);
        Tools.paintMImage(g, gai2, 240, -19 - time * 30, paint);
        g.drawBitmap(bt1, 96, 150 - time * 25, paint);

    }

    public void upData() {
        Log.i("Menu", "----------upData----------------" + mode);
        switch (mode) {
            case 0:
                if (time > 0) {
                    time--;
                    if (time <= 0) {
                        time = 0;
                        mode = 1;
                    }
                }
                if (!Game.isFrist) {
                    gameDraw.storyLine.free();
                }
                if (index == EXIT_GAME) {
                    gameDraw.gameExit.reset();
                    gameDraw.gameExit.init(gameDraw.res);
                }
                if (index == SETTING) {
                    gameDraw.setting.reset();
                    gameDraw.setting.init(gameDraw.res);
                }
                break;
            case 1:
                time++;
                if (time >= 10) {
                    time = 0;
                    mode = 2;
                }
                break;
            case 2:
                time++;
                time2--;
                if (time >= 10) {
                    time = 0;
                    time2 = 10;
                    mode = 3;
                }
                break;
            case 3:
                time++;
                if (time >= 10) {
                    time = 0;
                    mode = 4;
                }
                break;
            case 4:
                time++;
                if (time >= 10) {
                    gameDraw.dayGift.chack();
                    time = 0;
                    mode = 5;
                }
                break;
            case 5:
                switch (index) {
                    case NULL:
                        gameDraw.gameExit.free();
                        gameDraw.setting.free();
                        break;
                    case EXIT_GAME:
                        gameDraw.gameExit.reset();
                        gameDraw.gameExit.init(gameDraw.res);
                        break;
                    case SETTING:
                        gameDraw.setting.reset();
                        gameDraw.setting.init(gameDraw.res);
                        break;
                }
                break;
            case 6:
                if (index == NULL) {
                    time = time2--;
                    if (time2 <= 0) {
                        time2 = 10;
                        mode = 4;
                        gameDraw.achieve.free();
                        gameDraw.chooseBoss.free();
                        gameDraw.airplaneUpgrade.free();
                        gameDraw.help.free();
                        gameDraw.chooseAirplane.free();
                        gameDraw.storyLine.free();
                    }
                } else {
                    time++;
                    if (time == 0) {
                        switch (index) {
                            case ACHIEVE:
                                gameDraw.achieve.init(gameDraw.res);
                                break;
                            case BOSS:
                                gameDraw.chooseBoss.init(gameDraw.res);
                                break;
                            case UPGRADE:
                                gameDraw.airplaneUpgrade.init(gameDraw.res);
                                break;
                            case HELP:
                                gameDraw.help.init(gameDraw.res);
                                break;
                            case PLAYGAME:
                                gameDraw.chooseAirplane.init(gameDraw.res);
                                break;
                        }
                    }
                    if (time >= 10) {
                        switch (index) {
                            case ACHIEVE:
                                gameDraw.achieve.reset();
                                break;
                            case BOSS:
                                gameDraw.chooseBoss.reset();
                                Game.sm = Game.baseLife;
                                Game.baseLife = 3;
                                break;
                            case UPGRADE:
                                gameDraw.airplaneUpgrade.reset();
                                break;
                            case HELP:
                                gameDraw.help.reset();
                                break;
                            case PLAYGAME:
                                gameDraw.chooseAirplane.reset();
                                Game.sm = Game.baseLife;
                                Game.baseLife = 3;
                                Game.level = Data.level;
                                break;
                        }
                    }
                }
                break;
        }
    }

    public void touchDown(float tx, float ty) {
        Log.e("Menu", tx + "----------touchDown----------------" + ty);
        switch (mode) {
            case 5:
//                if (tx < 660 && ty > 900) {// 成就
//                    isDownAchieve = true;
//                    GameDraw.gameSound(1);
//                } else
                if (tx > 1230 && ty > 900) {// 声音设置
                    isDownSetting = true;
                    GameDraw.gameSound(1);
                } else if (tx == -100 && ty == -100) {// 退出游戏
                    isDownExit = true;
                    GameDraw.gameSound(1);
                    touchUp(-100, -100);
                } else if (tx > 960 - anStart.getWidth() / 2 && tx < 960 + anStart.getWidth() / 2 && ty > 900) {// 开始
                    isDownStart = true;
                    GameDraw.gameSound(1);
                } else if (ty > 355 && ty < 500 && tx > 680 && tx < 1250) {// 挑战BOSS
                    isDownBoss = true;
                    GameDraw.gameSound(1);
                } else if (ty > 500 && ty < 650 && tx > 680 && tx < 1250) {// 战机升级
                    isDownUpgrade = true;
                    GameDraw.gameSound(1);
                } else if (ty > 650 && ty < 700 && tx > 680 && tx < 1250) {// 帮助
//                    isDownHelp = true;
                    GameDraw.gameSound(1);
                } else if (tx < 200 && ty > 900) {
                    index = EXIT_GAME;
                    isDownExit = false;
                }

        }
    }

    public void touchUp(float tx, float ty) {
        Log.i("Menu", "----------touchUp----------------");
        switch (mode) {
            case 5:
//                if ((tx < 660 && ty > 900) && isDownAchieve) {// 成就
//                    isDownAchieve = false;
//                    index = ACHIEVE;
//                    mode = 6;
//                    time = -1;
//                } else
                if ((tx > 1230 && ty > 900) && isDownSetting) {// 声音设置
                    isDownSetting = false;
                    index = SETTING;
                } else if (tx == -100 && ty == -100) {// 退出游戏
                    if (isDownExit) {
                        index = EXIT_GAME;
                        isDownExit = false;
                    }
                } else if ((tx > 660 && tx < 1230 && ty > 900)
                        && isDownStart) {// 开始
                    isDownStart = false;
                    isLevelOrBoss = 1;
                    index = PLAYGAME;
                    mode = 6;
                    time = -1;
                } else if (ty > 355 && ty < 500 && tx > 680 && tx < 1250) {// 挑战BOSS
//                    if (s[0] && isDownBoss) {
                    isDownBoss = false;
                    isLevelOrBoss = 2;
                    index = BOSS;
                    mode = 6;
                    time = -1;
//                    } else if (!s[0] && isDownBoss) {
//                        gameDraw.smallDialog.reset(1, 240, 380, 10);
//                    }
                    isDownBoss = false;
                } else if (ty > 500 && ty < 650 && tx > 680 && tx < 1250) {// 战机升级
//                    if (s[1] && isDownUpgrade) {
                    isDownUpgrade = false;
                    index = UPGRADE;
                    mode = 6;
                    time = -1;
//                    } else if (!s[1] && isDownUpgrade) {
//                        gameDraw.smallDialog.reset(1, 240, 380, 10);
//                    }
                    isDownUpgrade = false;
                } else if (tx < 200 && ty > 900) {
                    index = EXIT_GAME;
                    isDownExit = false;
                }
//                else if ((ty > 650 && ty < 700 && tx > 680 && tx < 1250)
//                        && isDownHelp) {// 帮助
//                    isDownHelp = false;
//                    index = HELP;
//                    mode = 6;
//                    time = -1;
//
//                }
        }
    }

    public void touchMove(float tx, float ty) {
        Log.i("Menu", "----------touchMove----------------");
        switch (mode) {
            case 5:
//                if (!(tx < 660 && ty > 900) && isDownAchieve) {// 成就
//                    isDownAchieve = false;
//                } else
                if (!(tx > 1230 && ty > 900) && isDownSetting) {// 声音设置
                    isDownSetting = false;
                } else if (!(tx > 660 && tx < 1230 && ty > 900)
                        && isDownStart) {// 开始
                    isDownStart = false;
                } else if (!(ty > 355 && ty < 500 && tx > 680 && tx < 1250)
                        && isDownBoss) {// 挑战BOSS
                    isDownBoss = false;
                    isDownBoss = false;
                } else if (!(ty > 500 && ty < 650 && tx > 680 && tx < 1250)
                        && isDownUpgrade) {// 战机升级
                    isDownUpgrade = false;
                    isDownUpgrade = false;
                } else if (tx < 200 && ty > 900) {
                    index = EXIT_GAME;
                    isDownExit = false;
                }
//                else if (!(ty > 650 && ty < 700 && tx > 680 && tx < 1250)
//                        && isDownHelp) {// 帮助
//                    isDownHelp = false;
//                }
        }
    }

    public void keyDown(int k) {
        GameDraw.gameSound(1);
        switch (k) {
            case KeyEvent.KEYCODE_DPAD_UP://向上
                Log.e("jamie", "－－－－－向上－－－－－");
                switch (keyType) {
                    case 0://挑战Bss
                        keyType = 3;
                        break;
                    case 1://战斗升级
                        keyType = 0;
                        break;
                    case 2: //帮助关于
                        keyType = 1;
                        break;
                    case 3: //开始按钮
                        keyType = 2;
                        break;
                    case 4:  //成就
                        keyType = 2;
                        break;
                    case 5: //设置
                        keyType = 2;
                        break;
                    case 6:
                        keyType = 7;
                        break;
                    case 7:
                        keyType = 6;
                        break;
//                    case 8:
//                        keyType = 9;
//                        break;
//                    case 9:
//                        keyType = 8;
//                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN://向下
                Log.e("jamie", "－－－－－向下－－－－－");
                switch (keyType) {
                    case 0://挑战Bss
                        keyType = 1;
                        break;
                    case 1://战斗升级
                        keyType = 2;
                        break;
                    case 2: //帮助关于
                        keyType = 3;
                        break;
                    case 3: //开始按钮
                        keyType = 0;
                        break;
                    case 4:  //成就
                        keyType = 0;
                        break;
                    case 5: //设置
                        keyType = 0;
                        break;
                    case 6:
                        keyType = 7;
                        break;
                    case 7:
                        keyType = 6;
                        break;
//                    case 8:
//                        keyType = 9;
//                        break;
//                    case 9:
//                        keyType = 8;
//                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT://向左
                Log.e("jamie", "－－－－－向左－－－－－");
                switch (keyType) {
                    case 0://挑战Bss
                        keyType = 4;
                        break;
                    case 1://战斗升级
                        keyType = 4;
                        break;
                    case 2: //帮助关于
                        keyType = 4;
                        break;
                    case 3: //开始按钮
                        keyType = 4;
                        break;
                    case 4:  //成就
                        keyType = 5;
                        break;
                    case 5: //设置
                        keyType = 3;
                        break;
                    case 6:
                        keyType = 7;
                        break;
                    case 7:
                        keyType = 6;
                        break;
//                    case 8:
//                        keyType = 9;
//                        break;
//                    case 9:
//                        keyType = 8;
//                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT://向右
                Log.e("jamie", "－－－－－向右－－－－－");
                switch (keyType) {
                    case 0://挑战Bss
                        keyType = 5;
                        break;
                    case 1://战斗升级
                        keyType = 5;
                        break;
                    case 2: //帮助关于
                        keyType = 5;
                        break;
                    case 3: //开始按钮
                        keyType = 5;
                        break;
                    case 4:  //成就
                        keyType = 3;
                        break;
                    case 5: //设置
                        keyType = 4;
                        break;
                    case 6:
                        keyType = 7;
                        break;
                    case 7:
                        keyType = 6;
                        break;
//                    case 8:
//                        keyType = 9;
//                        break;
//                    case 9:
//                        keyType = 8;
//                        break;
                }
                break;
            case KeyEvent.KEYCODE_ENTER://确定
                Log.e("jamie", "－－－－－确定－－－－－");
                switch (keyType) {
                    case 0://挑战Bss
                        isDownBoss = false;
                        isLevelOrBoss = 2;
                        index = BOSS;
                        mode = 6;
                        time = -1;
                        isDownBoss = false;
                        break;
                    case 1://战斗升级
                        isDownUpgrade = false;
                        index = UPGRADE;
                        mode = 6;
                        time = -1;
                        isDownUpgrade = false;
                        break;
                    case 2: //帮助关于
//                        isDownHelp = false;
//                        index = HELP;
//                        mode = 6;
//                        time = -1;
                        break;
                    case 3: //开始按钮
                        isDownStart = false;
                        isLevelOrBoss = 1;
                        index = PLAYGAME;
                        mode = 6;
                        time = -1;
                        break;
                    case 4:  //成就
                        isDownAchieve = false;
                        index = ACHIEVE;
                        mode = 6;
                        time = -1;
                        break;
                    case 5: //设置
                        isDownSetting = false;
                        index = SETTING;
                        break;
                    case 6:
                        mode = 5;
                        break;
                    case 7:
                        isDownExit = true;
                        break;
//                    case 8:
//                        mode = 5;
//                        keyType = 3;
//                        break;
//                    case 9:
//                        GameDraw.isRun = false;
//                        MainActivity.main.finish();
//                        break;
                }
                break;
            case KeyEvent.KEYCODE_BACK://返回
                Log.e("jamie", "－－－－－返回－－－－－");
//                keyType = 6;
                index = EXIT_GAME;
                isDownExit = false;
                break;
            case KeyEvent.KEYCODE_HOME://房子
                Log.e("jamie", "－－－－－房子－－－－－");
                break;
            case KeyEvent.KEYCODE_MENU://菜单
                Log.e("jamie", "－－－－－菜单－－－－－");
                break;
        }
    }
}
