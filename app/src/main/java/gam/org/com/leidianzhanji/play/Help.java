package gam.org.com.leidianzhanji.play;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.KeyEvent;

import gam.org.com.leidianzhanji.MainActivity;
import gam.org.com.leidianzhanji.R;

public class Help {
    private boolean isDownReturn = false;
    GameDraw gameDraw;
    Bitmap im, bt;
    Bitmap an1, an2;
    Bitmap zi2;

    Bitmap gai2;
    Bitmap bt1;

    Bitmap bs_huan;


    int mode, time, id, time2;

    int t1, t2;

    public Help(GameDraw _mc) {
        gameDraw = _mc;
    }

    public void init(Resources res) {
        im = BitmapFactory.decodeResource(gameDraw.res, R.drawable.help_im);
        an1 = BitmapFactory.decodeResource(gameDraw.res, R.drawable.help_help);
        an2 = BitmapFactory.decodeResource(gameDraw.res, R.drawable.help_back);

        gai2 = BitmapFactory.decodeResource(res, R.drawable.menu_gai2);
        bt1 = BitmapFactory.decodeResource(res, R.drawable.menu_bt1);

        zi2 = Tools.getImageFromAssetsFile("help.png", MainActivity.main);
    }

    public void free() {
        im = null;
        an1 = null;
        an2 = null;
        bt = null;
        zi2 = null;
        bt1 = null;
        gai2 = null;
    }

    public void reset() {
        mode = 0;
        time2 = 0;
        time = 10;
        t1 = 0;
        t2 = 0;

        GameDraw.gameSound(2);

        gameDraw.canvasIndex = GameDraw.CANVAS_HEFP;
    }

    int bs_huan_t = 0;
    int keyType = 0;

    /**
     * 选择光圈的绘制
     */
    public void renderAN(Canvas g, boolean huan, Paint paint) {
        if (huan) {
            switch (keyType) {
                case 0:// 帮助
                    g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 900 - (bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 900 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 1:// 返回
                    g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 900 - (bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 900 + (bs_huan_t * 10 + 40)), paint);
                    break;
            }
            bs_huan_t--;
            if (bs_huan_t < 0)
                bs_huan_t = 10;
        }
    }


    public void render(Canvas g, Paint paint) {
        g.drawBitmap(Menu.bg, 0, 0, paint);
        switch (mode) {
            case 0:
                g.drawBitmap(gai2, 960 - gai2.getWidth(), 0, paint);
                Tools.paintMImage(g, gai2, 960, 0, paint);
                g.drawBitmap(bt1, 960 - bt1.getWidth() / 2, 98, paint);

                g.drawBitmap(im, 960 - im.getWidth() / 2, 350, paint);
                g.drawBitmap(an1, 960 - an1.getWidth() - 30, 900, paint);
                g.drawBitmap(an2, 960 + 30, 900, paint);
                break;
            case 1:
                g.drawBitmap(gai2, 960 - gai2.getWidth(), 0, paint);
                Tools.paintMImage(g, gai2, 960, 0, paint);
                g.drawBitmap(bt1, 960 - bt1.getWidth() / 2, 98, paint);
                g.drawBitmap(im, 960 - im.getWidth() / 2, 350, paint);
                g.drawBitmap(an1, 960 - an1.getWidth() - 30, 900, paint);
                g.drawBitmap(an2, 960 + 30, 900, paint);
                break;
            case 2:
                g.drawBitmap(gai2, 960 - gai2.getWidth(), 0, paint);
                Tools.paintMImage(g, gai2, 960, 0, paint);
                g.drawBitmap(bt1, 960 - bt1.getWidth() / 2, 98, paint);
                g.drawBitmap(im, 960 - im.getWidth() / 2, 350, paint);
                g.drawBitmap(an1, 960 - an1.getWidth() - 30, 900, paint);
                g.drawBitmap(an2, 960 + 30, 900, paint);
                break;
        }
        renderAN(g, true, paint);
    }

    public void upData() {
        switch (mode) {
            case 0:
                time--;
                time2++;
                if (time <= 0) {
                    time2 = 10;
                    mode = 1;
                    id = 1;
                }
                break;
            case 1:
                if (id == 1) {
                    if (t1 < 5)
                        t1++;
                    if (t2 > 0)
                        t2--;
                } else if (id == 2) {
                    if (t2 < 5)
                        t2++;
                    if (t1 > 0)
                        t1--;
                }
                break;
            case 2:
                time++;
                time2--;
                if (time >= 10) {
                    Menu.index = Menu.NULL;
                    gameDraw.menu.reset2();
                }
                break;
        }
    }

    public void touchDown(float tx, float ty) {
        switch (mode) {
            case 1:
                if (ty > 640 && ty < 720) {
                    if (tx < 240) {
                        id = 1;
                        GameDraw.gameSound(1);
                    } else {
                        id = 2;
                        GameDraw.gameSound(1);
                    }
                } else if (ty > 720 && tx > 320) {// 锟斤拷锟斤拷
                    isDownReturn = true;
                    GameDraw.gameSound(1);
                }
                break;
        }
    }

    public void touchUp(float tx, float ty) {
        switch (mode) {
            case 1:
                if ((ty > 720 && tx > 320) && isDownReturn) {// 锟斤拷锟斤拷
                    isDownReturn = false;
                    mode = 2;
                    time = 0;
                }
                break;
        }
    }

    public void touchMove(float tx, float ty) {
        switch (mode) {
            case 1:
                if (!(ty > 720 && tx > 320) && isDownReturn) {// 锟斤拷锟斤拷
                    isDownReturn = false;
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
                Log.e("jamie", "－－－－－向左－－－－－");
                switch (keyType) {
                    case 0:
                        keyType = 1;
                        break;
                    case 1:
                        keyType = 0;
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
                        keyType = 0;
                        break;
                }
                break;
            case 23://确定
            case KeyEvent.KEYCODE_ENTER://确定
                Log.e("jamie", "－－－－－确定－－－－－");
                switch (keyType) {
                    case 0:
                        id = 1;
//                        id = 2;
                        keyType = 1;
                        break;
                    case 1:
                        mode = 2;
                        time = 0;
                        keyType = 0;
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
