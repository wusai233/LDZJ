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
 * 成就界面
 */
public class Achieve {
    private boolean isDownReturn = false;
    GameDraw gameDraw;
    Bitmap top;
    Bitmap[] zi = new Bitmap[30];
    Bitmap[] pai = new Bitmap[3];
    Bitmap di1, di2;
    Bitmap dian1, dian2;
    Bitmap shu;
    Bitmap back_1;
    Bitmap back_2;
    Bitmap boss_an3_A;
    Bitmap bs_huan;

    int mode, time, id;
    float dx, tx, ox, vx;
    boolean isDown;

    public static boolean[] cj = new boolean[30];
    byte[] jp = new byte[]{1, 1, 0, 2, 0, 0, 0, 2, 0, 0, 1, 0, 2, 1, 0, 2, 1,
            0, 2, 1, 0, 0, 0, 0, 0, 0, 2, 1, 0, 2};

    public Achieve(GameDraw _mc) {
        gameDraw = _mc;
    }

    public void init(Resources res) {
        top = BitmapFactory.decodeResource(gameDraw.res, R.drawable.ry_top);

        di1 = BitmapFactory.decodeResource(gameDraw.res, R.drawable.ry_im1);
        di2 = BitmapFactory.decodeResource(gameDraw.res, R.drawable.ry_im2);

        dian1 = BitmapFactory.decodeResource(gameDraw.res, R.drawable.ry_y1);
        dian2 = BitmapFactory.decodeResource(gameDraw.res, R.drawable.ry_y2);

        shu = BitmapFactory.decodeResource(gameDraw.res, R.drawable.ry_shu);

        pai[0] = BitmapFactory.decodeResource(gameDraw.res, R.drawable.ry_xz1);
        pai[1] = BitmapFactory.decodeResource(gameDraw.res, R.drawable.ry_xz2);
        pai[2] = BitmapFactory.decodeResource(gameDraw.res, R.drawable.ry_xz3);

        back_1 = BitmapFactory.decodeResource(gameDraw.res, R.drawable.qh_back1);
        back_2 = BitmapFactory.decodeResource(gameDraw.res, R.drawable.qh_back2);

        boss_an3_A = BitmapFactory.decodeResource(gameDraw.res, R.drawable.boss_an3_1);

        for (int i = 0; i < zi.length; i++) {
            zi[i] = BitmapFactory.decodeResource(
                    res, res.getIdentifier("ry_zi" + i, "drawable", GameDraw.context.getPackageName()));


            bs_huan = BitmapFactory.decodeResource(gameDraw.res,
                    R.drawable.bs_huan_im);
        }
    }


    /**
     * 按钮类型 0：左滑动   1：返回  2：右滑动
     */
    int keyType = 1;

    int bs_huan_t = 0;

    /**
     * 选择光圈的绘制
     */
    public void renderAN(Canvas g, boolean huan, Paint paint) {
        if (huan) {
            switch (keyType) {
                case 0:
                    g.drawBitmap(bs_huan, null, new RectF(814 - (bs_huan_t * 10 + 40), 1016 - (bs_huan_t * 10 + 40), 814 + (bs_huan_t * 10 + 40), 1016 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 1:
                    // 返回
                    g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 1016 - (bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 1016 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 2:
                    g.drawBitmap(bs_huan, null, new RectF(1120 - (bs_huan_t * 10 + 40), 1016 - (bs_huan_t * 10 + 40), 1120 + (bs_huan_t * 10 + 40), 1016 + (bs_huan_t * 10 + 40)), paint);
                    break;
            }
            bs_huan_t--;
            if (bs_huan_t < 0)
                bs_huan_t = 10;
        }
    }

    public void free() {
        top = null;
        di1 = null;
        di2 = null;
        dian1 = null;
        dian2 = null;
        back_1 = null;
        back_2 = null;
        for (int i = 0; i < pai.length; i++) {
            pai[i] = null;
        }
        for (int i = 0; i < zi.length; i++) {
            zi[i] = null;
        }
    }

    public void reset() {
        mode = 0;
        time = 0;
        id = 0;

        if (GameDraw.isSound) {
            GameDraw.gameSound(2);
        }

        gameDraw.canvasIndex = GameDraw.CANVAS_ACHIEVE;
    }

    public void render(Canvas g, Paint paint) {
        g.drawBitmap(Menu.bg, 0, 0, paint);
        g.drawBitmap(back_2, 960 - back_2.getWidth() / 2, 980, paint);
        g.drawBitmap(back_1, 960 - back_1.getWidth() / 2, 980, paint);

        g.drawBitmap(boss_an3_A, 1065, 981, paint);
        Tools.paintMImage(g, boss_an3_A, 744, 981, paint);

        switch (mode) {
            case 0:
            case 20:
                g.drawBitmap(top, 664, 51, paint);
                paint.setAlpha(time * 25);
                for (int i = 0; i < 5; i++) {
                    renderPai(g, id * 5 + i, 732, 250 + i * 120, paint);
                }
                paint.setAlpha(255);
                break;
            case 1:
                g.drawBitmap(top, 664, 51, paint);
                for (int i = 0; i < 5; i++) {
                    renderPai(g, id * 5 + i, 732, 250 + i * 120, paint);
                }
                break;
            case 2:
                g.drawBitmap(top, 664, 51, paint);
                for (int i = 0; i < 5; i++) {
                    renderPai(g, id * 5 + i, 25 + dx + 100, 140 + i * 120, paint);
                    if (vx < 0) {
                        renderPai(g, ((id + 1) % 6) * 5 + i + 100, 732 + 480, 250 + i * 120, paint);
                    } else {
                        renderPai(g, ((id + 5) % 6) * 5 + i + 200, 732 + 480, 250 + i * 120, paint);
                    }
                }
                break;
        }
        if (mode == 1 || mode == 2) {
            for (int i = 0; i < 6; i++) {
                if (i == id) {
                    g.drawBitmap(dian2, 780 + i * 66, 892, paint);
                } else {
                    g.drawBitmap(dian1, 794 + i * 65, 906, paint);
                }
            }
            Bitmap bitmap = Tools.paintNum(shu, (int) Game.mnuey, -3);
            g.drawBitmap(bitmap, 795 - bitmap.getWidth() / 2, 164, paint);
            bitmap = Tools.paintNum(shu, (int) Game.score, -3);
            g.drawBitmap(bitmap, 958 - bitmap.getWidth() / 2, 164, paint);
            bitmap = Tools.paintNum(shu, (int) Game.npcNum, -3);
            g.drawBitmap(bitmap, 1115 - bitmap.getWidth() / 2, 164, paint);
            bitmap = null;
        }
        renderAN(g, true, paint);
    }

    public void renderPai(Canvas g, int id, float x, float y, Paint paint) {
        g.drawBitmap(di1, x, y, paint);
        if (cj[id] == true) {
            g.drawBitmap(di2, x, y, paint);
            g.drawBitmap(pai[jp[id]], x + 45, y + 30, paint);
        }

        g.drawBitmap(zi[id], x + 140, y + 46, paint);
    }

    public void upData() {
        switch (mode) {
            case 0:
                time++;
                if (time >= 10) {
                    time = 0;
                    mode = 1;
                    dx = 0;
                }
                break;
            case 1:
                if (isDown == true) {
                    dx += tx - ox;
                    ox = tx;
                } else if (dx != 0) {
                    if (dx > 0) {
                        dx -= 20;
                        if (dx < 0) {
                            dx = 0;
                        }
                    } else if (dx < 0) {
                        dx += 20;
                        if (dx > 0) {
                            dx = 0;
                        }
                    }
                }
                if (dx > 25) {
                    vx = 80;
                    mode = 2;
                    isDown = false;
                } else if (dx < -25) {
                    vx = -80;
                    mode = 2;
                    isDown = false;
                }
                break;
            case 2:
                dx += vx;
                if (dx > 480) {
                    dx = 0;
                    mode = 1;
                    id = (id + 5) % 6;
                } else if (dx < -480) {
                    dx = 0;
                    mode = 1;
                    id = (id + 1) % 6;
                }
                break;
            case 20:
                time--;
                if (time <= 0) {
                    Menu.index = Menu.NULL;
                    gameDraw.menu.reset2();
                }
                break;
        }
    }

    public void touchDown(float tx, float ty) {
        switch (mode) {
            case 1:
                if (tx > 960 - back_1.getWidth() / 2 && tx < 960 + back_1.getWidth() / 2 && ty > 954 && ty < 1048) {// 返回
                    isDownReturn = true;
                    GameDraw.gameSound(1);
                } else if (ty > 130 && ty < 720 && dx == 0) {
                    isDown = true;
                    this.tx = ox = tx;
                }
                break;
        }
    }

    public void touchUp(float tx, float ty) {
        if ((tx > 960 - back_1.getWidth() / 2 && tx < 960 + back_1.getWidth() / 2 && ty > 954 && ty < 1048) && isDownReturn) {// 返回
            isDownReturn = false;
            mode = 20;
            time = 10;
        }
        if (isDown) {
            isDown = false;
        }
    }

    public void touchMove(float tx, float ty) {
        if (!(tx > 960 - back_1.getWidth() / 2 && tx < 960 + back_1.getWidth() / 2 && ty > 954 && ty < 1048) && isDownReturn) {// 返回
            isDownReturn = false;
        }
        if (isDown) {
            this.tx = tx;
        }
    }

    public void keyUp(int k){
        Log.e("keyUp","--------"+keyType+"------");
        Log.e("keyUp","----点击之前：tx ="+tx+"------");
        switch (k){
            case KeyEvent.KEYCODE_ENTER:
            case 23:{//确定
                switch (keyType) {
                    case 0://上一頁
                        this.tx+=50;
                        isDown = false;
                        break;
                    case 1:
                        break;
                    case 2://下一页
                        this.tx-=50;
                        isDown = false;
                        break;
                }
            }
        }
        Log.e("keyUp","----点击之后：tx ="+tx+"------");
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
                switch (keyType) {
                    case 0:
                        keyType = 2;
                        break;
                    case 1:
                        keyType = 0;
                        break;
                    case 2:
                        keyType = 1;
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT://向右
                Log.e("jamie", "－－－－－向右－－－－－");
                switch (keyType) {
                    case 0:
                        keyType = 1;
                        break;
                    case 1:
                        keyType = 2;
                        break;
                    case 2:
                        keyType = 0;
                        break;
                }
                break;
            case 23://确定
                Log.e("jamie", "－－－－－确定－－－－－");
                switch (keyType) {
                    case 0:
                        GameDraw.gameSound(1);
                        this.tx = ox = tx=300;
                        isDown = true;
                        break;
                    case 1:
                        GameDraw.gameSound(1);
                        isDownReturn = true;
                        mode = 20;
                        time = 10;
                        isDownReturn = true;
                        break;
                    case 2:
                        GameDraw.gameSound(1);
                        this.tx = ox = tx=300;
                        isDown = true;
                        break;
                }
                break;
            case KeyEvent.KEYCODE_ENTER://确定
                GameDraw.gameSound(1);
                Log.e("jamie", "－－－－－确定－－－－－");
                switch (keyType) {
                    case 0:
                        this.tx = ox = tx=300;
                        isDown = true;
                        break;
                    case 1:
                        isDownReturn = true;
                        mode = 20;
                        time = 10;
                        isDownReturn = true;
                        break;
                    case 2:
                        this.tx = ox = tx=300;
                        isDown = true;
                        break;
                }
                break;
            case KeyEvent.KEYCODE_BACK://返回
                Log.e("jamie", "－－－－－返回－－－－－");
                mode = 20;
                time = 10;
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