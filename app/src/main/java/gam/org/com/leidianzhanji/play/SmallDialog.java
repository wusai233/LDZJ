package gam.org.com.leidianzhanji.play;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import gam.org.com.leidianzhanji.R;

/**
 * 弹框提示
 */
public class SmallDialog {
    GameDraw gameDraw;
    Bitmap im, zi, ry;

    float x, y;
    int mode, t, id;
    int to;

    public SmallDialog(GameDraw _mc) {
        gameDraw = _mc;
    }

    public void init(Resources res) {
        im = BitmapFactory.decodeResource(res, R.drawable.ts_im);
        if (id == 2) {
            zi = BitmapFactory.decodeResource(res, R.drawable.ts_yjmj);
        } else if (id < 5) {
            zi = BitmapFactory.decodeResource(res, res.getIdentifier("ts_zi"
                    + id, "drawable", GameDraw.context.getPackageName()));
        } else if (id < 40) {
            ry = BitmapFactory.decodeResource(res, R.drawable.ts_ry);
            zi = BitmapFactory
                    .decodeResource(res, res.getIdentifier("ry_zi" + (id - 10),
                            "drawable", GameDraw.context.getPackageName()));
        }
    }

    public void free() {
        im = null;
        zi = null;
        ry = null;
    }

    public void reset(int _id, float _x, float _y, int _to) {
        mode = 0;
        t = 0;
        id = _id;
        to = _to;
        x = _x;
        y = _y;

        if (id > 5) {
            y = 350;
        }

        gameDraw.canvasIndex = GameDraw.CANVAS_SMALL_DIALOG;
    }

    public void render(Canvas g, Paint paint) {
        gameDraw.paint(g, to);
        switch (mode) {
            case 0:
            case 20:
                g.drawColor((t * 60) << 24);
                break;
            case 1:
            case 21:
                g.drawColor(180 << 24);
                g.drawBitmap(im, 960 - im.getWidth() / 2, 520, paint);
                g.drawBitmap(im, 960 - im.getWidth() / 2, 520, paint);
                paint.setAlpha(t * 30 + 100);
                if (id > 5) {
                    g.drawBitmap(ry, 960 - ry.getWidth() / 2, 490, paint);
                }
                paint.setAlpha(255);
                break;
            case 2:
                g.drawColor(180 << 24);
                g.drawBitmap(im, 960 - im.getWidth() / 2, 520, paint);
                if (id < 5) {
                    g.drawBitmap(zi, 960 - zi.getWidth() / 2, 538, paint);
                } else if (id < 40) {
                    g.drawBitmap(ry, 960 - ry.getWidth() / 2, 490, paint);
                    g.drawBitmap(zi, 960 - zi.getWidth() / 2, 538, paint);
                }
                break;
        }
    }

    public void upData() {
        switch (mode) {
            case 0:
                t++;
                if (t >= 3) {
                    t = 0;
                    mode = 1;
                } else if (t == 1) {
                    this.init(gameDraw.res);
                }
                break;
            case 1:
                t++;
                if (t >= 5) {
                    t = 0;
                    mode = 2;
                }
                break;
            case 2:
                t++;
                if (t >= 30) {
                    t = 5;
                    mode = 21;
                } else if (t == 15) {
                    Data.save();
                }
                break;
            case 21:
                t--;
                if (t <= 0) {
                    t = 3;
                    mode = 20;
                }
                break;
            case 20:
                t--;
                if (t <= 0) {
                    gameDraw.canvasIndex = (byte) to;
                    if (id == 3) {
                        if (ChooseAirplane.haveAirplane[2] == false) {
                            // PaymentJoy.getInstance(mc.sp).startCharge(new
                            // PaymentParam(11));
                            gameDraw.chooseAirplane.buyID = 3;
                            gameDraw.billingDialog.reset(30, 15);
                        } else {
                            gameDraw.chooseAirplane.id = 3;
                            Airplane.id = gameDraw.chooseAirplane.id + 1;
                            gameDraw.chooseAirplane.airPlaneBullet.reset();
                            Airplane.y = 210;
                        }
                    }
                } else if (t == 2) {
                    this.free();
                } else if (t == 1) {
                    if (MainActivity.gcDebug) {
                        System.gc();
                    }
                }
                break;
        }
    }

}
