package gam.org.com.leidianzhanji.play;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;

import gam.org.com.leidianzhanji.R;

public class GameWin {
    private String TAG="GameWin";
    private boolean isDownCJ = false;
    public boolean isDownUpgrade = false;
    private boolean isDownGoOn = false;
    public static final int MAX_LEVEL = 12;// 最多关卡数
    final int mv = 60;

    GameDraw gameDraw;
    Bitmap im1, im2, shu, kuang;
    Bitmap an1, an2, an3, an4, an5, an6;
    Bitmap di;
    Bitmap[] fs = new Bitmap[3];
    Bitmap[] jiang = new Bitmap[6];
    Bitmap bs_huan;

    int mode, time, id, anid;
    float dx, vx;

    int l;

    public GameWin(GameDraw _mc) {
        gameDraw = _mc;
    }

    public void init(Resources res) {
        im1 = BitmapFactory.decodeResource(res, R.drawable.sl_bg1);
        im2 = BitmapFactory.decodeResource(res, R.drawable.sl_bg3);
        an1 = BitmapFactory.decodeResource(res, R.drawable.cj_cj1);
        an2 = BitmapFactory.decodeResource(res, R.drawable.cj_cj2);
        an3 = BitmapFactory.decodeResource(res, R.drawable.cj_upground1);
        an4 = BitmapFactory.decodeResource(res, R.drawable.cj_upground2);
        an5 = BitmapFactory.decodeResource(res, R.drawable.cj_jixu1);
        an6 = BitmapFactory.decodeResource(res, R.drawable.cj_jixu2);
        di = BitmapFactory.decodeResource(res, R.drawable.cj_im);
        shu = BitmapFactory.decodeResource(res, R.drawable.game_shu);
        kuang = BitmapFactory.decodeResource(res, R.drawable.cj_kuang);
        fs[0] = BitmapFactory.decodeResource(res, R.drawable.cj_df);
        fs[1] = BitmapFactory.decodeResource(res, R.drawable.cj_sj);
        fs[2] = BitmapFactory.decodeResource(res, R.drawable.cj_sd);
        jiang[0] = BitmapFactory.decodeResource(res, R.drawable.cj_i1);
        jiang[1] = BitmapFactory.decodeResource(res, R.drawable.cj_i2);
        jiang[2] = BitmapFactory.decodeResource(res, R.drawable.cj_i3);
        jiang[3] = BitmapFactory.decodeResource(res, R.drawable.cj_i4);
        jiang[4] = BitmapFactory.decodeResource(res, R.drawable.sp_liang1);
        jiang[5] = BitmapFactory.decodeResource(res, R.drawable.sp_liang2);

        bs_huan = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.bs_huan_im);
    }

    public void free() {
        im1 = null;
        im2 = null;
        shu = null;
        an1 = null;
        an2 = null;
        an3 = null;
        an4 = null;
        an5 = null;
        an6 = null;
        di = null;
        kuang = null;
        for (int i = 0; i < fs.length; i++) {
            fs[i] = null;
        }
        for (int i = 0; i < jiang.length; i++) {
            jiang[i] = null;
        }
    }

    public void reset() {
        mode = 0;
        time = 0;
        id = 5;
        dx = 0;
        GameDraw.isPlayMusic(GameDraw.gameMediaPlayer,
                GameDraw.bossMediaPlayer, GameDraw.menuMediaPlayer);
        if (ChooseAirplane.haveAirplane[0] == false) {
            l = 15;
        } else if (ChooseAirplane.haveAirplane[1] == false) {
            l = 14;
        } else {
            l = 13;
        }
        GameDraw.gameSound(2);
        gameDraw.canvasIndex = GameDraw.CANVAS_GAME_WIN;
    }

    public void draw(Canvas g, Paint paint, int time) {
        g.drawBitmap(im1, 960 - im1.getWidth(), 34, paint);
        Tools.paintMImage(g, im1, 960, 34, paint);
        g.drawBitmap(im2, 960 - im2.getWidth(), 455, paint);
        Tools.paintMImage(g, im2, 960, 455, paint);
        g.drawBitmap(Game.down, 960 - Game.down.getWidth(), 1000 - time * (float) 13.7, paint);
        Tools.paintMImage(g, Game.down, 960, 1000 - time * (float) 13.7, paint);
    }

    int bs_huan_t = 0;
    int keyType = 1;//0 升級 1抽獎 2繼續

    /**
     * 选择光圈的绘制
     */

    public void renderAN(Canvas g, boolean huan, Paint paint) {
        if (huan) {
            Log.d(TAG,"-----renderAN-----"+keyType);
            switch (keyType) {
                case 0:
                    g.drawBitmap(bs_huan, null, new RectF(799 - (bs_huan_t * 10 + 40), 816 - (bs_huan_t * 10 + 40), 799 + (bs_huan_t * 10 + 40), 816 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 1:
                    g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 816 + -(bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 816 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 2:
                    g.drawBitmap(bs_huan, null, new RectF(1120 - (bs_huan_t * 10 + 40), 816 - (bs_huan_t * 10 + 40), 1120 + (bs_huan_t * 10 + 40), 816 + (bs_huan_t * 10 + 40)), paint);
                    break;
            }
            bs_huan_t--;
            if (bs_huan_t < 0)
                bs_huan_t = 10;
        }
    }

    public void render(Canvas g, Paint paint) {
        g.drawBitmap(Menu.bg, 0, 0, paint);
        if(gameDraw.getGift.isDone == true){
            keyType = 0 ;
            gameDraw.getGift.isDone = false;
        }
        switch (mode) {
            case 0:
                gameDraw.game.render(g, paint);
                draw(g, paint, time);
                break;
            case 1:
                keyType = 1;
                draw(g, paint, 10);
                paint.setAlpha(time * 25 + 5);
                renderJM(g, paint);
                paint.setAlpha(255);
                g.drawBitmap(an1, 847, 762, paint);
                break;
            case 2:
                keyType = 1;
                draw(g, paint, 10);
                renderJM(g, paint);
                if (isDownCJ)
                    g.drawBitmap(an1, 847, 762, paint);
                else
                    g.drawBitmap(an2, 847, 762, paint);
                break;
            case 3:
                draw(g, paint, 10);
                renderJM(g, paint);
                break;
            case 4:
                keyType = 1;
                draw(g, paint, 10);
                renderJM(g, paint);
                g.drawBitmap(an1, 847, 762, paint);
                break;
            case 5:
                an1 = null;
                an2 = null;
                draw(g, paint, 10);
                renderJM(g, paint);
                if (isDownUpgrade)
                    g.drawBitmap(an4, 682, 760, paint);
                else
                    g.drawBitmap(an3, 682, 760, paint);
                if (isDownGoOn)
                    g.drawBitmap(an6, 1003, 759, paint);
                else
                    g.drawBitmap(an5, 1003, 759, paint);
                break;
            case 10:
                draw(g, paint, 10);
                paint.setAlpha(time * 25 + 5);
                renderJM(g, paint);
                g.drawBitmap(an3, 682, 760, paint);
                g.drawBitmap(an5, 1003, 759, paint);
                paint.setAlpha(255);
                break;
            case 21:
                gameDraw.airplaneUpgrade.render(g, paint);
                draw(g, paint, time);
                break;
            case 22:
                gameDraw.chooseAirplane.render(g, paint);
                draw(g, paint, time);
                break;
        }
        renderAN(g, true, paint);
    }

    public void renderJM(Canvas g, Paint paint) {
        for (int i = 0; i < 3; i++) {
            g.drawBitmap(di, 800, 115 + 76 * i, paint);
            Tools.paintMImage(g, di, 960, 115 + 76 * i, paint);
            g.drawBitmap(fs[i], 820, 123 + 76 * i, paint);
        }
        Bitmap bitmap = Tools.paintNum(shu, (int) Game.score, -2);
        g.drawBitmap(bitmap, 930, 121, paint);
        bitmap = Tools.paintNum(shu, (int) Game.mnuey, -2);
        g.drawBitmap(bitmap, 930, 199, paint);
        bitmap = Tools.paintNum(shu, (int) Game.npcNum, -2);
        g.drawBitmap(bitmap, 930, 273, paint);
        bitmap = null;

        renderCJ(g, id, dx, paint);
        g.drawBitmap(kuang, 960 - kuang.getWidth(), 505, paint);
        Tools.paintMImage(g, kuang, 960, 505, paint);

    }

    public void renderCJ(Canvas g, int id, float dx, Paint paint) {
        if (dx == 0) {
            renderJiang(g, id, 960, 590, paint);
            renderJiang(g, (id + 1) % l, 1200, 590, paint);
            renderJiang(g, (id + l - 1) % l, 760, 590, paint);
        } else if (dx < 0) {
            renderJiang(g, id, 960 + dx, 590, paint);
            renderJiang(g, (id + 1) % l, 1200 + dx, 590, paint);
            renderJiang(g, (id + 2) % l, 1200 + dx, 590, paint);
            renderJiang(g, (id + l - 1) % l, 760 + dx, 590, paint);
        }
        Log.e("动画结束","---------"+mode+"-------");
    }


    public void renderJiang(Canvas g, int id, float x, float y, Paint paint) {
        Bitmap bitmap = null;
        switch (id) {
            case 0:
                bitmap = Tools.paintNum(shu, 100, 0);
                g.drawBitmap(jiang[3], x - 25, y - 30, paint);
                g.drawBitmap(bitmap, x - 35, y + 15, paint);
                break;
            case 1:
                bitmap = Tools.paintNum(shu, 1, 0);
                g.drawBitmap(jiang[2], x - 32, y - 37, paint);
                g.drawBitmap(bitmap, x, y + 15, paint);
                break;
            case 2:
                bitmap = Tools.paintNum(shu, 500, 0);
                g.drawBitmap(jiang[3], x - 25, y - 30, paint);
                g.drawBitmap(bitmap, x - 35, y + 15, paint);
                break;
            case 3:
                bitmap = Tools.paintNum(shu, 3, 0);
                g.drawBitmap(jiang[1], x - 32, y - 37, paint);
                g.drawBitmap(bitmap, x, y + 15, paint);
                break;
            case 4:
                bitmap = Tools.paintNum(shu, 100, 0);
                g.drawBitmap(jiang[3], x - 25, y - 30, paint);
                g.drawBitmap(bitmap, x - 35, y + 15, paint);
                break;
            case 5:
                g.drawBitmap(jiang[0], x - 70, y - 47, paint);
                break;
            case 6:
                bitmap = Tools.paintNum(shu, 1, 0);
                g.drawBitmap(jiang[1], x - 32, y - 37, paint);
                g.drawBitmap(bitmap, x, y + 15, paint);
                break;
            case 7:
                bitmap = Tools.paintNum(shu, 1, 0);
                g.drawBitmap(jiang[2], x - 32, y - 37, paint);
                g.drawBitmap(bitmap, x, y + 15, paint);
                break;
            case 8:
                bitmap = Tools.paintNum(shu, 100, 0);
                g.drawBitmap(jiang[3], x - 25, y - 30, paint);
                g.drawBitmap(bitmap, x - 35, y + 15, paint);
                break;
            case 9:
                bitmap = Tools.paintNum(shu, 500, 0);
                g.drawBitmap(jiang[3], x - 25, y - 30, paint);
                g.drawBitmap(bitmap, x - 35, y + 15, paint);
                break;
            case 10:
                bitmap = Tools.paintNum(shu, 1000, 0);
                g.drawBitmap(jiang[3], x - 25, y - 30, paint);
                g.drawBitmap(bitmap, x - 44, y + 15, paint);
                break;
            case 11:
                bitmap = Tools.paintNum(shu, 500, 0);
                g.drawBitmap(jiang[3], x - 25, y - 30, paint);
                g.drawBitmap(bitmap, x - 35, y + 15, paint);
                break;
            case 12:
                bitmap = Tools.paintNum(shu, 100, 0);
                g.drawBitmap(jiang[3], x - 25, y - 30, paint);
                g.drawBitmap(bitmap, x - 35, y + 15, paint);
                break;
            case 13:
                g.drawBitmap(jiang[5], x - 70, y - 45, paint);
                break;
            case 14:
                g.drawBitmap(jiang[4], x - 70, y - 45, paint);
                break;
        }
        bitmap = null;
    }

    public void upData() {
        switch (mode) {
            case 0:
                time++;
                if (time >= 10) {
                    time = 0;
                    gameDraw.game.free();
                    if (Data.level == 2 && ChooseAirplane.haveAirplane[2] == false) {
                        gameDraw.billingDialog.reset(30, 22);
                    }
                    nextLevel();
                    mode = 1;
                }
                break;
            case 1:
                time++;
                if (time >= 10) {
                    time = 0;
                    mode = 2;
                }
                break;
            case 3:
                time--;
                // if(SeletPlayer.have[0] == false)
                // if (false)
                // {
                // if (id == 6 && t <= 100) {
                // t = 0;
                // }
                // if (t > 0) {
                // dx -= vx;
                // vx += 3;
                // if (vx > mv)
                // vx = mv;
                // } else {
                // dx -= vx;
                // vx -= (mv - 10) / 20;
                // if (vx <= 10) {
                // vx = 10;
                // }
                // if (dx < -135 && id == 13) {
                // jlMove();
                // dx = 0;
                // m = 5;
                // t = 0;
                // getJiang(id);
                // return;
                // }
                // }
                // } else
                // {
                if (time > 0) {
                    dx -= vx;
                    vx += 3;
                    if (vx > mv)
                        vx = mv;
                } else {
                    dx -= vx;
                    vx -= (mv - 10) / 20;
                    if (vx <= 10) {
                        vx = 10;
                        if (dx > -15 && id != 5 && id != 13 && id != 14) {
                            dx = 0;
                            mode = 5;
                            time = 0;
                            getJiang(id);
                            return;
                        } else if (dx < -135 && id != 4 && id != 12 && id != 13) {
                            jlMove();
                            dx = 0;
                            mode = 5;
                            time = 0;
                            getJiang(id);
                            return;
                        }
                    }
                }
                // }
                if (dx <= -150) {
                    jlMove();
                }
                break;
            case 5:
                break;
            case 10:
                time--;
                if (time <= 0) {
                    time = 10;
                    GameDraw.gameSound(2);

                    switch (anid) {
                        case 1:
                            gameDraw.airplaneUpgrade.init(gameDraw.res);
                            gameDraw.airplaneUpgrade.reset2();
                            mode = 21;
                            break;
                        case 2:
                            gameDraw.chooseAirplane.init(gameDraw.res);
                            gameDraw.chooseAirplane.reset2();
                            mode = 22;
                            break;
                    }
                }
                break;
            case 21:
                time--;
                if (time <= 0) {
                    gameDraw.canvasIndex = GameDraw.CANVAS_AIRPLANE_UPGRADE;
                    if (AirplaneUpgrade.jx == true && MainActivity.isFirstPlay) {
                        gameDraw.npcIntroduce.reset(5, 18);
                    }
                }
                break;
            case 22:
                time--;
                if (time <= 0) {
                    gameDraw.canvasIndex = GameDraw.CANVAS_CHOOSE_AIRPLANE;
                }
                break;
        }
    }

    /**
     * 下一关
     */
    public void nextLevel() {
        if (Game.level <= MAX_LEVEL) {
            if (ChooseBoss.jd[Game.level - 1] == 0) {
                ChooseBoss.jd[Game.level - 1] = 1;
            }
        }
        // if(SeletPlayer.have[0] == false)
        // {
        // if(Game.level >= 4)
        // {
        // SeletPlayer.have[0] = true ;
        // }
        // }
        // if(SeletPlayer.have[1] == false)
        // {
        // if(Game.level >= 6)
        // {
        // SeletPlayer.have[1] = true ;
        // }
        // }
        // if(SeletPlayer.have[2] == false)
        // {
        // if(Game.level >= 10)
        // {
        // SeletPlayer.have[2] = true ;
        // }
        // }
        if (Data.level <= Game.level) {
            Data.level++;
        }
        Game.level++;
        if (Game.level > MAX_LEVEL) {
            Data.level = MAX_LEVEL;
            Game.level = MAX_LEVEL;
        }
        if (Menu.s[0] == false) {
            Menu.s[0] = true;
            Menu.s[1] = true;
        }
        Data.save();
    }

    /**
     * 抽取奖励时获取的物品
     */
    public void getJiang(int id) {
        switch (id) {
            case 0:
                Game.mnuey += 100;
                break;
            case 1:
                Game.baohu += 1;
                break;
            case 2:
                Game.mnuey += 500;
                break;
            case 3:
                Game.bisha += 3;
                break;
            case 4:
                Game.mnuey += 100;
                break;
            case 5:
                ChooseAirplane.haveAirplane[2] = true;
                break;
            case 6:
                Game.bisha += 1;
                break;
            case 7:
                Game.baohu += 1;
                break;
            case 8:
                Game.mnuey += 100;
                break;
            case 9:
                Game.mnuey += 500;
                break;
            case 10:
                Game.mnuey += 1000;
                break;
            case 11:
                Game.mnuey += 500;
                break;
            case 12:
                Game.mnuey += 100;
                break;
            case 13:
                ChooseAirplane.haveAirplane[1] = true;
                break;
            case 14:
                ChooseAirplane.haveAirplane[0] = true;
                break;
        }
        gameDraw.getGift.reset(10 + id, 22);
    }

    public void jlMove() {
        id = (id + 1) % l;
        dx += 150;
    }

    public void touchDown(float tx, float ty) {
        switch (mode) {
            case 2:
                if ((tx > 847 && tx < 1073 && ty > 762 && ty < 869)) {// 抽奖
                    isDownCJ = true;
                    GameDraw.gameSound(1);
                }
                break;
            case 5:
                if (tx > 687 && tx < 920 && ty > 759 && ty < 870) {// 升级战机界面
                    isDownUpgrade = true;
                    GameDraw.gameSound(1);
                } else if (tx > 1004 && tx < 1237 && ty > 759 && ty < 870) {// 选择战机界面
                    isDownGoOn = true;
                    GameDraw.gameSound(1);
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public void touchUp(float tx, float ty) {
        switch (mode) {
            case 2:
                if ((tx > 847 && tx < 1073 && ty > 762 && ty < 869) && isDownCJ) {// 抽奖
                    isDownCJ = false;
                    mode = 3;
                    time = Math.abs(GameDraw.random.nextInt() % 60) + 40;
                    // if(SeletPlayer.have[0] == false)
                    // if (false) {
                    // t = 150;
                    // }
                }
                break;
            case 5:
                if ((tx > 687 && tx < 920 && ty > 759 && ty < 870) && isDownUpgrade) {
                    isDownUpgrade = false;
                    anid = 1;
                    time = 10;
                    mode = 10;
                } else if ((tx > 1004 && tx < 1237 && ty > 759 && ty < 870)
                        && isDownGoOn) {
                    isDownGoOn = false;
                    anid = 2;
                    time = 10;
                    mode = 10;
                }
                break;
        }
    }

    public void touchMove(float tx, float ty) {
        switch (mode) {
            case 2:
                if (!(tx > 847 && tx < 1073 && ty > 762 && ty < 869) && isDownCJ) {// 抽奖
                    isDownCJ = false;
                }
                break;
            case 5:
                if (!(tx > 687 && tx < 920 && ty > 759 && ty < 870) && isDownUpgrade) {
                    isDownUpgrade = false;
                } else if (!(tx > 1004 && tx < 1237 && ty > 759 && ty < 870)
                        && isDownGoOn) {
                    isDownGoOn = false;
                }
                break;
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
                Log.e("jamie", "－－－－－按钮向左－－－－－");
                switch (keyType){
                    case 0:
                        keyType = 2;
                        break;
                    case 2:
                        keyType = 0;
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT://向右
                Log.e("jamie", "－－－－－按钮向右－－－－－");
                switch (keyType){
                    case 0:
                        keyType = 2;
                        break;
                    case 2:
                        keyType = 0;
                        break;
                }
                break;
            case KeyEvent.KEYCODE_ENTER://确定
            case 23://确定
                Log.e("jamie", "－－－－－确定－－－－－");
                switch (keyType) {
                    case 0:
                        anid = 1;
                        time = 10;
                        mode = 10;
                        break;
                    case 1:
                        mode = 3;
                        time = Math.abs(GameDraw.random.nextInt() % 60) + 40;
                        keyType = 4;
                        break;
                    case 2:
                        anid = 2;
                        time = 10;
                        mode = 10;
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

}
