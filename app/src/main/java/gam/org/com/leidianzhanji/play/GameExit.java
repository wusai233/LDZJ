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

public class GameExit {
    private boolean isDownExit = false;
    private boolean isDownBack = false;
    GameDraw gameDraw;
    Bitmap liangYes, anYes, zi, background, back;
    int mode, time, id;
    Bitmap bs_huan;

    int bs_huan_t = 0;
    int keyType = 1;

    /**
     * 选择光圈的绘制
     */
    public void renderAN(Canvas g, boolean huan, Paint paint) {
        if (huan) {
            switch (keyType) {
                case 0:
                    g.drawBitmap(bs_huan, null, new RectF(1080 + back.getWidth() / 2 + -(bs_huan_t * 10 + 40), 310 + back.getHeight() / 2 - (bs_huan_t * 10 + 40), 1080 + back.getWidth() / 2 + (bs_huan_t * 10 + 40), 310 + back.getHeight() / 2 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 1:
                    g.drawBitmap(bs_huan, null, new RectF(960 + -(bs_huan_t * 10 + 40), 475 + anYes.getHeight() / 2 - (bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 475 + anYes.getHeight() / 2 + (bs_huan_t * 10 + 40)), paint);
                    break;
            }
            bs_huan_t--;
            if (bs_huan_t < 0)
                bs_huan_t = 10;
        }
    }

    public GameExit(GameDraw _mc) {
        gameDraw = _mc;
    }

    public void init(Resources res) {
        bs_huan = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.bs_huan_im);
        liangYes = BitmapFactory.decodeResource(res, R.drawable.exit_yes1);
        anYes = BitmapFactory.decodeResource(res, R.drawable.exit_yes2);
        zi = BitmapFactory.decodeResource(res, R.drawable.exit_zi);
        background = BitmapFactory.decodeResource(res, R.drawable.sz_im);
        back = BitmapFactory.decodeResource(res, R.drawable.sz_back1);
    }

    public void free() {
        liangYes = null;
        anYes = null;
        zi = null;
        background = null;
        back = null;
    }

    int a = 0;

    public void reset() {
        mode = 0;
        time = 0;
        id = 0;
        gameDraw.canvasIndex = GameDraw.CANVAS_GAME_EXIT;
    }

    public void render(Canvas g, Paint paint) {
        switch (mode) {
            case 0:
                gameDraw.menu.render(g, paint);
                break;
            case 1:
                gameDraw.menu.render(g, paint);
                g.drawColor(0x99000000);
                g.drawBitmap(background, 960 - background.getWidth(), 280, paint);
                Tools.paintMImage(g, background, 960, 280, paint);
                Tools.paintM2Image(g, background, 960 - background.getWidth(), 280 + 172, paint);
                Tools.paintRotateImage(g, background, 960, 280 + 172, 180, 192,
                        172, paint);
                g.drawBitmap(zi, 960 - zi.getWidth() / 2, 380, paint);
                if (isDownBack)
                    g.drawBitmap(back, 1080, 310, paint);
                else
                    g.drawBitmap(back, 1080, 310, paint);
                if (isDownExit)
                    g.drawBitmap(anYes, 960 - anYes.getWidth() / 2, 475, paint);
                else
                    g.drawBitmap(liangYes, 960 - liangYes.getWidth() / 2, 475, paint);
                if (id == 1) {
                    Menu.index = Menu.NULL;
                    gameDraw.canvasIndex = GameDraw.CANVAS_MENU;
                } else if (id == 2) {
                    GameDraw.isRun = false;
                    MainActivity.main.finish();
                }
                break;
        }
        renderAN(g, true, paint);
    }

    public void upData() {
        switch (mode) {
            case 0:
                mode = 1;
                break;
        }
    }

    public void touchDown(float tx, float ty) {
        switch (mode) {
            case 1:
                if (tx > 150 && tx < 325 && ty > 470 && ty < 560) {// 退出
                    isDownExit = true;
                    GameDraw.gameSound(1);
                } else if (tx > 350 && ty > 300 && tx < 415 && ty < 355) {// 继续
                    isDownBack = true;
                    GameDraw.gameSound(1);
                }
                break;
        }
    }

    public void touchUp(float tx, float ty) {
        switch (mode) {
            case 1:
                if ((tx > 150 && tx < 325 && ty > 470 && ty < 560) && isDownExit) {// 退出
                    isDownExit = false;
                    id = 2;
                } else if ((tx > 350 && ty > 300 && tx < 415 && ty < 355)
                        && isDownBack) {// 继续
                    isDownBack = false;
                    id = 1;
                }
                break;
        }
    }

    public void touchMove(float tx, float ty) {
        switch (mode) {
            case 1:
                if (!(tx > 150 && tx < 325 && ty > 470 && ty < 560) && isDownExit) {// 退出
                    isDownExit = false;
                } else if (!(tx > 350 && ty > 300 && tx < 415 && ty < 355)
                        && isDownBack) {// 继续
                    isDownBack = false;
                }
                break;
        }
    }

    public void keyDown(int k) {
        switch (k) {
            case KeyEvent.KEYCODE_DPAD_UP://向上
                Log.e("jamie", "－－－－－向上－－－－－");
                switch (keyType) {
                    case 0:
                        keyType = 1;
                        break;
                    case 1:
                        keyType = 0;
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN://向下
                Log.e("jamie", "－－－－－向下－－－－－");
                switch (keyType) {
                    case 0:
                        keyType = 1;
                        break;
                    case 1:
                        keyType = 0;
                        break;
                }
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
                Log.e("jamie", "－－－－－确定－－－－－");
                GameDraw.gameSound(1);
                switch (keyType) {
                    case 0:
                        isDownBack = true;
                        id = 1;
                        break;
                    case 1:
                        isDownExit = false;
                        id = 2;
                        break;
                }
//                touchDown(200, 500);
//                touchUp(200, 500);
                break;
            case KeyEvent.KEYCODE_BACK://返回
                Log.e("jamie", "－－－－－返回－－－－－");
                GameDraw.gameSound(1);
                isDownBack = true;
                id = 1;
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
