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

public class NewStoryLine {
    GameDraw gameDraw;
    Bitmap background;
    Bitmap zi1, zi2, zi3, zi4, an;
    Bitmap bs_huan;
    int mode, time;

    public NewStoryLine(GameDraw gd) {
        gameDraw = gd;
    }

    public void init(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.sl_background);
        zi1 = BitmapFactory.decodeResource(res, R.drawable.sl_zi1);
        zi2 = BitmapFactory.decodeResource(res, R.drawable.sl_zi2);
        zi3 = BitmapFactory.decodeResource(res, R.drawable.sl_zi3);
        zi4 = BitmapFactory.decodeResource(res, R.drawable.sl_zi4);
        an = BitmapFactory.decodeResource(res, R.drawable.jq_tiao);

        bs_huan = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.bs_huan_im);
    }

    int bs_huan_t = 0;

    /**
     * 选择光圈的绘制
     */
    public void renderAN(Canvas g, boolean huan, Paint paint) {
        if (huan) {
            g.drawBitmap(bs_huan, null, new RectF(960 - (bs_huan_t * 10 + 40), 884 - (bs_huan_t * 10 + 40), 960 + (bs_huan_t * 10 + 40), 884 + (bs_huan_t * 10 + 40)), paint);
            bs_huan_t--;
            if (bs_huan_t < 0)
                bs_huan_t = 10;
        }
    }

    public void free() {
        background = null;
        zi1 = null;
        zi2 = null;
        zi3 = null;
        zi4 = null;
    }

    public void reset() {
        mode = 0;
        time = 0;
        gameDraw.canvasIndex = GameDraw.CANVAS_FIRST_STORY_LINE;
        Game.isFrist = false;
        Data.save();
    }

    public void render(Canvas g, Paint paint) {
        renderAN(g, mode > 2, paint);
        switch (mode) {
            case 0:
                paint.setAlpha(time * 10);
                g.drawBitmap(background, 0, 0, paint);
                break;
            case 1:
                paint.setAlpha(255);
                g.drawBitmap(background, 0, 0, paint);
                paint.setAlpha(time * 10);
                g.drawBitmap(an, 960 - an.getWidth() / 2, 860, paint);
                g.drawBitmap(zi1, 960 - zi1.getWidth() / 2, 70, paint);
                break;
            case 2:
                paint.setAlpha(255);
                g.drawBitmap(background, 0, 0, paint);
                g.drawBitmap(an, 960 - an.getWidth() / 2, 860, paint);
                g.drawBitmap(zi1, 960 - zi1.getWidth() / 2, 70, paint);
                paint.setAlpha(time * 10);
                g.drawBitmap(zi2, 960 - zi2.getWidth() / 2, 225, paint);
                break;
            case 3:
                paint.setAlpha(255);
                g.drawBitmap(background, 0, 0, paint);
                g.drawBitmap(an, 960 - an.getWidth() / 2, 860, paint);
                g.drawBitmap(zi1, 960 - zi1.getWidth() / 2, 70, paint);
                g.drawBitmap(zi2, 960 - zi2.getWidth() / 2, 225, paint);
                paint.setAlpha(time * 10);
                g.drawBitmap(zi3, 960 - zi3.getWidth() / 2, 385, paint);
                break;
            case 4:
                paint.setAlpha(255);
                g.drawBitmap(background, 0, 0, paint);
                g.drawBitmap(zi1, 960 - zi1.getWidth() / 2, 70, paint);
                g.drawBitmap(zi2, 960 - zi2.getWidth() / 2, 225, paint);
                g.drawBitmap(zi3, 960 - zi3.getWidth() / 2, 385, paint);
                g.drawBitmap(an, 960 - an.getWidth() / 2, 860, paint);
                paint.setAlpha(time * 10);
                g.drawBitmap(zi4, 960 - zi4.getWidth() / 2, 695, paint);
                break;
            case 5:
                paint.setAlpha(255 - time * 10);
                g.drawBitmap(background, 0, 0, paint);
                g.drawBitmap(an, 960 - an.getWidth() / 2, 860, paint);
                paint.setAlpha(255);
                g.drawBitmap(zi1, 960 - zi1.getWidth() / 2, 70, paint);
                g.drawBitmap(zi2, 960 - zi2.getWidth() / 2, 225, paint);
                g.drawBitmap(zi3, 960 - zi3.getWidth() / 2, 385, paint);
                g.drawBitmap(zi4, 960 - zi4.getWidth() / 2, 695, paint);
                break;
        }
    }

    public void upData() {
        switch (mode) {
            case 0:
                time++;
                if (time >= 25) {
                    time = 0;
                    mode = 1;
                }
                break;
            case 1:
                time++;
                if (time >= 25) {
                    time = 0;
                    mode = 2;
                }
                break;
            case 2:
                time++;
                if (time >= 25) {
                    time = 0;
                    mode = 3;
                }
                break;
            case 3:
                time++;
                if (time >= 25) {
                    time = 0;
                    mode = 4;
                }
                break;
            case 4:
                time++;
                if (time >= 25) {
                    time = 0;
                    mode = 5;
                }
                break;
            case 5:
                time++;
                if (time >= 25) {
                    time = 0;
                    gameDraw.menu.init(gameDraw.res);
                    gameDraw.menu.reset();
                    GameDraw.isPlayMusic(GameDraw.bossMediaPlayer,
                            GameDraw.gameMediaPlayer, GameDraw.menuMediaPlayer);
                }
                break;
        }
    }

    public void touchDown(float tx, float ty) {
        if (tx > 884 && tx < 1033 && ty > 860 && ty < 914 && (mode == 2 || mode == 3 || mode == 5)) {
            GameDraw.gameSound(1);
            time = 0;
            mode = 5;
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
            case 23://确定
                Log.e("jamie", "－－－－－确定－－－－－");
                if ((mode == 2 || mode == 3 || mode == 5)) {
                    GameDraw.gameSound(1);
                    time = 0;
                    mode = 5;
                }
                break;
            case KeyEvent.KEYCODE_BACK://返回
                Log.e("jamie", "－－－－－返回－－－－－");
                if ((mode == 2 || mode == 3 || mode == 5)) {
                    GameDraw.gameSound(1);
                    time = 0;
                    mode = 5;
                }
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
