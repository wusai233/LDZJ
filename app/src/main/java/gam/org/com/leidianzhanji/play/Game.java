package gam.org.com.leidianzhanji.play;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.KeyEvent;

import gam.org.com.leidianzhanji.MainActivity;
import gam.org.com.leidianzhanji.R;
import gam.org.com.leidianzhanji.npc.ZL;

public class Game {
    private String TAG = "GAME";
    private boolean isDownPause = false;
    public static final int TOP = 0;
    public static final int BOTEM = 1050;
    public static final int CW = 1920;

    public static final int GG = 0;

    public static final int[] everyscore = new int[]{10, 20, 30, 40, 50, 60, 180};

    public static Game game;
    public static boolean isFrist = true;
    public static int level = 1;
    public static float cx, mx;
    public static float score = 0, mnuey = 0, npcNum = 0, zmnuey = 0;
    public static int sm = 3;
    public static int bisha = 3, baohu = 3;
    public static byte[] bishaNum = new byte[3];
    public static boolean isWD = false;// 判断是否无敌

    /**
     * 僚机个数
     */
    public static int wingNum = 0;
    /**
     * 僚机火力
     */
    public static float wingHL = 1;
    /**
     * 初始火力
     * 以修改成３
     */
    public static int baseHL = 3;
    /**
     * 玩家攻击
     */
    public static float attack = 1;
    /**
     * 暴击时间
     */
    public static int critTime = 80;
    /**
     * 必杀时间
     */
    public static int biShaTime = 30;
    /**
     * 保护上限
     */
    public static int protectNum = 1;
    /**
     * 初始生命
     */
    public static int baseLife = 3;

    public static int bosshp = 0;
    public static int bosshpmax = 0;
    public static int bossm = 0;
    public static int bosst = 0;

    public int bjt = 0;

    public GameDraw gameDraw;

    public GameBackground gameBackground;
    public Airplane airplane;
    public NPCManager npcManager;
    public AirplaneBullet airplaneBullet;
    public NPCBulletManager npcBulletManager;
    public BumpManager bumpManager;
    public BombManager bombManager;
    public BiShaManager skillManager;

    public static Bitmap top, down;
    public static Bitmap back, back2, sp_an, sp_an_hui;
    Bitmap df, sj, anBiSha, anHuDun, anPause, liangPause, shu;

    Bitmap uib1, uib2, uib3;
    Bitmap uibj1, uibj2;
    Bitmap bs_huan, bh_huan;
    Bitmap[] smim = new Bitmap[4];


    public static int isShuijing = 0;

    int tcbs_t, tcbs_fi, tcbs_x;
    Bitmap[] tcbs = new Bitmap[2];

    /**
     * 透明度的设置
     */
    int lb_alp, lb_av;
    Bitmap[] lb = new Bitmap[2];

    public static boolean isFang;

    public Game(GameDraw _mc) {
        game = this;
        gameDraw = _mc;

        gameBackground = new GameBackground();
        airplane = new Airplane(gameDraw);
        airplaneBullet = new AirplaneBullet(100);
        npcManager = new NPCManager(50);
        npcManager.zl = new ZL(gameDraw);
        npcBulletManager = new NPCBulletManager(500, this);
        bumpManager = new BumpManager(500);
        bombManager = new BombManager(500);
        skillManager = new BiShaManager(1);

        top = BitmapFactory.decodeResource(gameDraw.res, R.drawable.game_top);
        down = BitmapFactory.decodeResource(gameDraw.res, R.drawable.game_down);
        back = BitmapFactory
                .decodeResource(gameDraw.res, R.drawable.game_back1);
        back2 = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_back2);
        sp_an = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.sp_an);
        sp_an_hui = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.sp_an_hui);

        isWD = false;
    }

    public void init(Resources res) {
        gameBackground.init(res, level);
        airplane.init(res);
        npcManager.init(res);
        npcBulletManager.init(res);
        bumpManager.init(res);
        bombManager.init(res);
        skillManager.init(res);
        airplaneBullet.init(res);

        df = BitmapFactory.decodeResource(gameDraw.res, R.drawable.game_df);
        sj = BitmapFactory.decodeResource(gameDraw.res, R.drawable.game_sj);
        anBiSha = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_ui11);
        anHuDun = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_ui21);
        anPause = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_ui31);
        liangPause = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_ui32);
        shu = BitmapFactory.decodeResource(gameDraw.res, R.drawable.game_shu);

        uib1 = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_uiboss1);
        uib2 = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_uiboss2);
        uib3 = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_uiboss3);

        bs_huan = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.bs_huan_im);
        bh_huan = BitmapFactory
                .decodeResource(gameDraw.res, R.drawable.bsxg2_1);

        uibj1 = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_uijn1);
        uibj2 = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_uijn2);

        smim[0] = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_uism1);
        smim[1] = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_uism2);
        smim[2] = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_uism3);
        smim[3] = BitmapFactory.decodeResource(gameDraw.res,
                R.drawable.game_uism4);

        tcbs[0] = BitmapFactory.decodeResource(gameDraw.res, R.drawable.tcbs1);
        tcbs[1] = BitmapFactory.decodeResource(gameDraw.res, R.drawable.tcbs2);

        lb[0] = BitmapFactory.decodeResource(gameDraw.res, R.drawable.lb1);
        lb[1] = BitmapFactory.decodeResource(gameDraw.res, R.drawable.lb2);

        gameDraw.pause.init(res);
    }

    public void free() {
        gameBackground.free();
        airplane.free();
        npcManager.free();
        npcBulletManager.free();
        bumpManager.free();
        bombManager.free();
        skillManager.free();
        airplaneBullet.free();
        gameDraw.pause.free();

        df = null;
        sj = null;
        anBiSha = null;
        anHuDun = null;
        anPause = null;
        shu = null;
        uib1 = null;
        uib2 = null;
        uib3 = null;

        for (int i = 0; i < smim.length; i++) {
            smim[i] = null;
        }

        for (int i = 0; i < tcbs.length; i++) {
            tcbs[i] = null;
            lb[i] = null;
        }
    }

    public void newGame() {
        baohu = Data.bh;
        if (sm <= 0)
            sm = 1;
        Airplane.hl = Game.baseHL;
    }

    public void reset() {
        gameBackground.reset();
        airplane.reset();
        airplaneBullet.reset();
        npcManager.reset();
        npcBulletManager.reset();
        bumpManager.reset();
        bombManager.reset();
        skillManager.reset();
        cx = 0;
        mx = 0;

        bosshpmax = 0;
        bosst = 0;
        bjt = 0;
        Airplane.fh = false;

        tcbs_fi = 0;
        tcbs_t = 0;
        tcbs_x = 0;
        lb_alp = 0;
        lb_av = 30;

        isShuijing = 0;
        isFang = false;

        gameDraw.canvasIndex = GameDraw.CANVAS_GAME;
    }

    public void render(Canvas g, Paint paint) {
        gameBackground.render(g, paint);
        npcManager.render(g, paint);
        airplaneBullet.render(g, paint);
        skillManager.render(g, paint);
        airplane.renderLJ(g, paint);
        airplane.render(g, paint);
        bumpManager.render(g, paint);
        npcBulletManager.render(g, paint);
        bombManager.render(g, paint);
        renderUI(g, paint);
        renderAN(g, true, paint);
    }

    // 判断是否暂停
    boolean pause = false;

    int bs_huan_t = 0;
    /**
     * 右上角暂停的光圈
     */
    int keyType = 9;

    public void renderAN(Canvas g, boolean huan, Paint paint) {
        if (huan) {
            switch (keyType) {
                case 0:
                    g.drawBitmap(bs_huan, null, new RectF(142 - (bs_huan_t * 10 + 40), 310 - (bs_huan_t * 10 + 40), 142 + (bs_huan_t * 10 + 40), 310 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 1:
                    g.drawBitmap(bs_huan, null, new RectF(1822 - (bs_huan_t * 10 + 40), 58 - (bs_huan_t * 10 + 40), 1822 + (bs_huan_t * 10 + 40), 58 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 2:
                    g.drawBitmap(bs_huan, null, new RectF(123 - (bs_huan_t * 10 + 40), 985 - (bs_huan_t * 10 + 40), 123 + (bs_huan_t * 10 + 40), 985 + (bs_huan_t * 10 + 40)), paint);
                    break;
                case 3:
                    g.drawBitmap(bs_huan, null, new RectF(1791 - (bs_huan_t * 10 + 40), 985 - (bs_huan_t * 10 + 40), 1791 + (bs_huan_t * 10 + 40), 985 + (bs_huan_t * 10 + 40)), paint);
                    break;
            }
            bs_huan_t--;
            if (bs_huan_t < 0)
                bs_huan_t = 10;
        }
    }


    /**
     * 左下角必杀技能的绘制
     */
    public void renderBS(Canvas g, boolean huan, Paint paint) {
        g.drawBitmap(anBiSha, 51, 914, paint);
        g.drawBitmap(Tools.paintNum(shu, bisha, -4), 195, 987, paint);
        if (huan) {
            g.drawBitmap(bs_huan, null, new RectF(123 - (bs_huan_t * 10 + 40), 985 - (bs_huan_t * 10 + 40), 123 + (bs_huan_t * 10 + 40), 985 + (bs_huan_t * 10 + 40)), paint);
            bs_huan_t--;
            if (bs_huan_t < 0)
                bs_huan_t = 10;
        }
    }

    int bh_huan_t = 0;

    /**
     * 右下角护盾技能的绘制
     */
    public void renderBH(Canvas g, boolean huan, Paint paint) {
        g.drawBitmap(anHuDun, 1665, 914, paint);
        g.drawBitmap(Tools.paintNum(shu, baohu, -4), 1690, 987, paint);
        // Tools.paintNum(g, shu, 390, 740, baohu, -4, paint);
        if (huan) {
            g.drawBitmap(bh_huan, null, new RectF(
                    1791 - (bh_huan_t * 10 + 40),
                    985 - (bh_huan_t * 10 + 40),
                    1791 + (bh_huan_t * 10 + 40),
                    985 + (bh_huan_t * 10 + 40)), paint);
            bh_huan_t--;
            bh_huan_t--;
            if (bh_huan_t < 0)
                bh_huan_t = 10;
        }
    }


    public void renderUI(Canvas g, Paint paint) {
        renderBS(g, bisha <= 0, paint);
        renderBH(g, baohu <= 0, paint);
        if (isDownPause)
            g.drawBitmap(liangPause, 1788, GG + 20, paint);
        else
            g.drawBitmap(anPause, 1788, GG + 20, paint);

        g.drawBitmap(df, 27, GG + 20, paint);// 得分的绘制
        g.drawBitmap(Tools.paintNum(shu, (int) Game.score, -3), 136, GG + 29,
                paint);
        // Tools.paintNum(g, shu, 65, GG + 6, (int) Game.score, -3, paint);
        g.drawBitmap(sj, 380, GG + 20, paint);// 水晶的绘制
        g.drawBitmap(Tools.paintNum(shu, (int) Game.mnuey, -3), 538, GG + 29,
                paint);
        // Tools.paintNum(g, shu, 285, GG + 6, (int) Game.mnuey, -3, paint);

        if (bosst > 0) {
            int w = bosshp * 370 / bosshpmax;
            g.drawBitmap(uib1, -400 + bosst * 40, GG + 35, paint);
            if (Game.bossm == 1) {
                g.drawBitmap(uib2, 23 - 400 + bosst * 40, GG + 51, paint);
                g.drawBitmap(uib3, new Rect(0, 0, w, 19), new Rect(
                        23 - 400 + bosst * 40, GG + 51, 23 - 400 + bosst * 40
                        + w, GG + 51 + 19), paint);
            } else if (Game.bossm == 2) {
                g.drawBitmap(uib2, new Rect(0, 0, w, 19), new Rect(
                        23 - 400 + bosst * 40, GG + 51, 23 - 400 + bosst * 40
                        + w, GG + 51 + 19), paint);
            }
        }

        for (int i = 0; i < sm - 1; i++) {
            g.drawBitmap(smim[Airplane.id - 1], 30 + i * 50, GG + 80 + bosst
                    * 4.5f, paint);
        }

        g.drawBitmap(lb[0], 0, GG + 173 + bosst * 4.5f, paint);
        paint.setAlpha(lb_alp);
        g.drawBitmap(lb[1], 0, GG + 174 + bosst * 4.5f, paint);
        paint.setAlpha(255);

        if (bjt > 0) {
            int w = (critTime - Airplane.fft) * 132 / critTime;
            g.drawBitmap(uibj1, bjt * 50 - 150, GG + 85 + bosst * 4.5f, paint);
            g.drawBitmap(uibj2, new Rect(0, 0, w, 13), new Rect(
                    bjt * 50 - 150 + 16, (int) (GG + 85 + bosst * 4.5f) + 14,
                    bjt * 50 - 150 + w + 16,
                    (int) (GG + 85 + bosst * 4.5f) + 13 + 14), paint);
        }

        if (tcbs_x > 0) {
            // paint.setAlpha(150) ;
            g.drawBitmap(tcbs[(int) (tcbs_fi / 3)], 1920 - tcbs_x, 228, paint);
            tcbs_fi++;
            if (tcbs_fi >= 6)
                tcbs_fi = 0;
            // paint.setAlpha(255) ;
        }
    }

    public void upData() {
        lb_alp += lb_av;
        if (lb_alp > 255) {
            lb_alp = 255;
            lb_av = -Math.abs(lb_av);
        } else if (lb_alp < 0) {
            lb_alp = 0;
            lb_av = +Math.abs(lb_av);
        }

        gameBackground.upData();
        skillManager.upData(this);
        airplane.updata(airplaneBullet);
        airplane.fireLJ(airplaneBullet);
        cx = 0;
        Tools.BOSSMove();
        // mx = Airplane.x;
        airplaneBullet.updata(this);
        npcManager.upData(npcBulletManager);
        npcBulletManager.upData(this);
        bumpManager.upData(this);
        bombManager.upData();

        if (isShuijing > 0) {
            GameDraw.gameSound(5);
            isShuijing = 0;
        }

        addShuijing(0);

        if (bosshpmax > 0 && !(bossm == 2 && bosshp <= 0)) {
            if (bosst < 10) {
                bosst++;
                if (bosst >= 10) {
                    GameDraw.isPlayMusic(GameDraw.gameMediaPlayer,
                            GameDraw.menuMediaPlayer, GameDraw.bossMediaPlayer);
                }
            }
        } else {
            if (bosst > 0) {
                bosst--;
                if (bosst <= 0) {
                    GameDraw.isPlayMusic(GameDraw.bossMediaPlayer,
                            GameDraw.menuMediaPlayer, GameDraw.gameMediaPlayer);
                }
            }
        }
        if (Airplane.mode == 15) {
            if (bjt < 3)
                bjt++;
        } else {
            if (bjt > 0)
                bjt--;
        }

        if (Achieve.cj[16] == false) {
            if (score > 100000) {
                Achieve.cj[16] = true;
                gameDraw.smallDialog.reset(26, 960, GG + 60, 20);
            }
        }
        if (Achieve.cj[17] == false) {
            if (score > 200000) {
                Achieve.cj[17] = true;
                gameDraw.smallDialog.reset(27, 960, GG + 60, 20);
            }
        }
        if (Achieve.cj[18] == false) {
            if (score > 500000) {
                Achieve.cj[18] = true;
                gameDraw.smallDialog.reset(28, 960, GG + 60, 20);
            }
        }

        if (Data.jx == true) {
            if (Game.level == 1 && npcManager.zl.time == 550
                    && MainActivity.isFirstPlay) {
                if (baohu <= 0)
                    baohu = 1;
                gameDraw.npcIntroduce.reset(2, 20);
            } else if (Data.level == 1 && npcManager.zl.time == 1290
                    && MainActivity.isFirstPlay) {
                if (bisha <= 0)
                    bisha = 1;
                gameDraw.npcIntroduce.reset(3, 20);
            }
        }

        // 从右侧滑出暴击购买框
        if ((NPCManager.num + npcBulletManager.l >= 20 || NPCManager.num >= 4)
                && Data.level > 1) {
            if (tcbs_t < 0) {
                tcbs_t++;
            } else {
                tcbs_x += 28;
                if (tcbs_x >= 280) {
                    tcbs_x = 280;
                    tcbs_t = 50;
                }
            }
        } else {
            if (tcbs_x > 0) {
                if (tcbs_t > 0) {
                    tcbs_t--;
                } else {
                    tcbs_x -= 28;
                }
            } else {
                if (tcbs_t < 0) {
                    tcbs_t++;
                }
            }
        }
    }

    public void touchDown(float tx, float ty) {
        if (tx < 200 && ty < 200) {
            Log.e(TAG, "------------------暂停---------------110");
            gameDraw.canvasIndex = GameDraw.CANVAS_GAME;
        }
        if (tx < 283 && ty > GG + 172 + bosst * 4.5f && ty < GG + 416 + bosst * 4.5f + 134) {
            gameDraw.billingDialog.reset(40, 20);
            return;
        }
        if (tx > 1665 && tx < 1920 && ty > 914 && ty < 1080) {// 右下角护盾
            if (baohu > 0) {
                baohu--;
                npcBulletManager.bs(bumpManager);
                gameDraw.biShaBg.reset(2);
                GameDraw.gameSound(7);
                Data.chackBH();
            } else {
                if (MainActivity.isShowBuyMessage) {
                    gameDraw.billingDialog.reset(1, 20);
                } else {
                    gameDraw.pause.reset();
                    gameDraw.pause.mode = 1;
                    gameDraw.pause.t = 0;
//                    PaymentJoy.getInstance(gameDraw.pause).startCharge(
//                            new PaymentParam(7));
                }
            }
        } else if (tx > 1788 && tx < 1855 && ty > GG + 20 && ty < GG + 92) {// 右上角暂停
            isDownPause = true;
            GameDraw.gameSound(1);
        } else if (tx < 250 && ty > 914 && ty < 1080) {// 左下角必杀
            if (Airplane.mode == 0 || Airplane.mode == 15) {
                if (bisha > 0) {
                    GameDraw.gameSound(7);
                    bisha--;
                    gameDraw.biShaBg.reset(1);
                    npcBulletManager.reset();
                } else {
                    isFang = true;
                    if (MainActivity.isShowBuyMessage) {
                        gameDraw.billingDialog.reset(2, 20);
                    } else {
                        gameDraw.pause.reset();
                        gameDraw.pause.mode = 1;
                        gameDraw.pause.t = 0;
//						PaymentJoy.getInstance(gameDraw.pause).startCharge(
//								new PaymentParam(6));
                    }
                }
            }
            tcbs_t = -100;
            tcbs_x = 0;
        }
        airplane.touchDown(tx, ty);
    }

    public void touchUp(float tx, float ty) {
        if ((tx > 1788 && tx < 1855 && ty > GG + 20 && ty < GG + 92) && isDownPause) {// 右上角暂停
            isDownPause = false;
            gameDraw.pause.reset();
        }
        airplane.touchUp(tx, ty);
    }

    public void touchMove(float tx, float ty) {
        if (!(tx > 1788 && tx < 1855 && ty > GG + 20 && ty < GG + 92) && isDownPause) {// 右上角暂停
            isDownPause = false;
        }
        airplane.touchMove(tx, ty);
    }

    public void exit() {

    }

    public void addShuijing(float n) {
        mnuey += n;// 用于记录现有的水晶
        zmnuey += n;// 用于记录获得的所有水晶（包括用完的）
        if (Achieve.cj[13] == false) {
            if (zmnuey >= 10000) {
                Achieve.cj[13] = true;
                gameDraw.smallDialog.reset(23, 960, GG + 60,
                        gameDraw.canvasIndex);
            }
        }
        if (Achieve.cj[14] == false) {
            if (zmnuey >= 50000) {
                Achieve.cj[14] = true;
                gameDraw.smallDialog.reset(24, 960, GG + 60,
                        gameDraw.canvasIndex);
            }
        }
        if (Achieve.cj[15] == false) {
            if (zmnuey >= 100000) {
                Achieve.cj[15] = true;
                gameDraw.smallDialog.reset(25, 960, GG + 60,
                        gameDraw.canvasIndex);
            }
        }
    }

    public static void addPlayerHL() {
        if (Airplane.hl < 3) {
            Airplane.hl++;
        } else {
            if (Airplane.mode != 21 && Airplane.mode != 22
                    && Airplane.mode != 11) {
                Airplane.ZhuangTai(15);
            }
        }
    }

//    public static void drawTop(Canvas g, Paint paint, int t) {
//        g.drawBitmap(top, 0, t * (float) 15.9 - 159, paint);
//        Tools.paintMImage(g, top, 960, t * (float) 15.9 - 159, paint);
//    }

    public static void drawDown(Canvas g, Paint paint, int t, boolean isDown) {
        g.drawBitmap(down, 572, 949 - t * (float) 13.7, paint);
        Tools.paintMImage(g, down, 960, 949 - t * (float) 13.7, paint);
        if (isDown) {
            g.drawBitmap(back2, 631, 1110 - t * (float) 13.7, paint);
        } else {
            g.drawBitmap(back, 631, 1110 - t * (float) 13.7, paint);
        }
        if (isDown) {
            g.drawBitmap(sp_an_hui, 1055, 1110 - t * (float) 13.7, paint);
        } else {
            g.drawBitmap(sp_an, 1055, 1110 - t * (float) 13.7, paint);
        }
    }

    public void keyDown(int k) {
        switch (k) {
            case KeyEvent.KEYCODE_DPAD_UP://向上
                GameDraw.gameSound(1);
                Log.e("jamie", "－－－－－向上－－－－－");
                airplane.touchDown(100, 100);
                airplane.touchMove(100, 70);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN://向下
                GameDraw.gameSound(1);
                Log.e("jamie", "－－－－－向下－－－－－");
                airplane.touchDown(100, 100);
                airplane.touchMove(100, 130);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT://向左
                GameDraw.gameSound(1);
                Log.e("jamie", "－－－－－向左－－－－－");
                airplane.touchDown(100, 100);
                airplane.touchMove(70, 100);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT://向右
                GameDraw.gameSound(1);
                Log.e("jamie", "－－－－－向右－－－－－");
                airplane.touchDown(100, 100);
                airplane.touchMove(130, 100);
                break;
            case KeyEvent.KEYCODE_ENTER://确定
            case 23://确定
                GameDraw.gameSound(1);
                Log.e("jamie", "－－－－－23－－－－－");
                Log.e("jamie", "－－－－－确定－－－－－");
                isDownPause = true;
                isDownPause = false;
                gameDraw.pause.reset();
                break;
            case KeyEvent.KEYCODE_BACK://返回
                GameDraw.gameSound(1);
                Log.e("jamie", "－－－－－返回－－－－－");
                gameDraw.canvasIndex = GameDraw.CANVAS_GAME;
                break;
            case KeyEvent.KEYCODE_HOME://房子
                GameDraw.gameSound(1);
                Log.e("jamie", "－－－－－房子－－－－－");
                break;
            case KeyEvent.KEYCODE_MENU://菜单
                GameDraw.gameSound(1);
                Log.e("jamie", "－－－－－菜单－－－－－");
                gameDraw.canvasIndex = GameDraw.CANVAS_GAME;
                break;
        }
    }

}
