package gam.org.com.leidianzhanji.play;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import gam.org.com.leidianzhanji.R;

import static android.R.id.content;
import static gam.org.com.leidianzhanji.R.drawable.bg11;

/**
 * 总游戏绘制
 */
public class GameDraw extends SurfaceView implements Runnable {
    public static final byte CANVAS_PROGRESS = 0;// 进入游戏时进度
    public static final byte CANVAS_NPC_INTRODUCE = 4;// NPC介绍游戏界面
    public static final byte CANVAS_SMALL_DIALOG = 5;// 游戏进行时弹出成就界面
    public static final byte CANVAS_BILLING_DIALOG = 6;// 护盾不足时补给、技能不足补给、超值礼包、立即复活、死亡后选择界面
    public static final byte CANVAS_GET_GIFT = 7;// 领取礼包
    public static final byte CANVAS_DAY_GIFT = 8;// 每日登陆礼包
    public static final byte CANVAS_MENU = 10;// 菜单界面
    public static final byte CANVAS_HEFP = 11;// 帮助界面
    public static final byte CANVAS_SETTING = 12;// 设置界面
    // public static final byte CANVAS_LOADING = 9;
    public static final byte CANVAS_ACHIEVE = 13;// 成就界面
    public static final byte CANVAS_CHOOSE_BOSS = 14;// 挑战BOSS选择界面
    public static final byte CANVAS_CHOOSE_AIRPLANE = 15;// 战机选择界面
    public static final byte CANVAS_LEVEL = 16;// 关卡界面
    public static final byte CANVAS_CHOOSE_BOSS_START = 17;// 挑战BOSS选择后开始和缓冲界面
    public static final byte CANVAS_AIRPLANE_UPGRADE = 18;// 战机升级界面
    public static final byte CANVAS_FIRST_STORY_LINE = 19;// 第一次剧情界面
    public static final byte CANVAS_GAME = 20; // 游戏界面
    public static final byte CANVAS_GAME_WIN = 22;// 任务完成
    public static final byte CANVAS_GAME_PAUSE = 25;// 游戏暂停界面
    public static final byte CANVAS_SKILL_BACKGROUND = 29;// 放技能、护盾时渲染界面
    public static final byte CANVAS_GAME_EXIT = 30;// 退出游戏界面

    public static boolean isRun = true;

    //    public static final int WIDTH = 480;
//    public static final int HEIGHT = 800;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;
    public static final int WITE_TIME = 55;
    // public static final int UPDATA_NUM = 1000 / WITE_TIME;

    public static GameDraw gameDraw;
    public static Random random = new Random();
    public byte canvasIndex = CANVAS_PROGRESS;
    public static boolean isSound;
    public static Paint red, black;
    public Resources res;
    static Context context;
    private SurfaceHolder mSurfaceHolder;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    // InputMethodManager input;

    // 申请部分
    public Menu menu;
    public Game game;
    public Loading loading;
    public ChooseAirplane chooseAirplane;
    public Level level;
    public Help help;
    public Setting setting;
    public Achieve achieve;
    public GamePause pause;
    public NewStoryLine storyLine;
    public GameWin gameWin;
    public AirplaneUpgrade airplaneUpgrade;
    public ChooseBoss chooseBoss;
    public SmallDialog smallDialog;
    public BillingDialog billingDialog;
    public Loading load;
    public DayGift dayGift;
    public GetGift getGift;
    public NPCIntroduce npcIntroduce;
    public BiShaBackground biShaBg;
    public GameExit gameExit;

    private int time;// 控制进度
    private int shakeIndex;// 用于抖动标记
    public static boolean isShake = false;// 判断是否抖动

    public static MediaPlayer menuMediaPlayer, gameMediaPlayer,
            bossMediaPlayer;

    public GameDraw(MainActivity context) {// 初始化
        super(context);
        gameDraw = this;
        GameDraw.context = context;
        mSurfaceHolder = this.getHolder();
        random = new Random();
        res = context.getResources();
        mBitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG));

        menu = new Menu(this);
        game = new Game(this);
        chooseAirplane = new ChooseAirplane(this);
        level = new Level(this);
        help = new Help(this);
        setting = new Setting(this);
        achieve = new Achieve(this);
        pause = new GamePause(this);
        storyLine = new NewStoryLine(this);
        airplaneUpgrade = new AirplaneUpgrade(this);
        gameWin = new GameWin(this);
        biShaBg = new BiShaBackground(this);
        chooseBoss = new ChooseBoss(this);
        smallDialog = new SmallDialog(this);
        loading = new Loading(this);
        load = new Loading(this);
        billingDialog = new BillingDialog(this);
        dayGift = new DayGift(this);
        getGift = new GetGift(this);
        npcIntroduce = new NPCIntroduce(this);
        gameExit = new GameExit(this);

        red = new Paint(Paint.ANTI_ALIAS_FLAG);
        ColorMatrix cm = new ColorMatrix();
        cm.set(new float[]{1, 0, 0, 0, 100, 0, 1, 0, 0, 100, 0, 0, 1, 0, 100,
                0, 0, 0, 1, 0});
        red.setColorFilter(new ColorMatrixColorFilter(cm));

        black = new Paint(Paint.ANTI_ALIAS_FLAG);
        cm = new ColorMatrix();
        cm.set(new float[]{0.08f, 0, 0, 0, 0, 0, 0.08f, 0, 0, 0, 0, 0, 0.08f,
                0, 0, 0, 0, 0, 1, 0});
        black.setColorFilter(new ColorMatrixColorFilter(cm));

        canvasIndex = CANVAS_PROGRESS;
        isShake = false;
        time = 20;
        shakeIndex = 0;
        isSound = true;
        (new Thread(this)).start();
    }

    /**
     * 判断是否开启音乐
     */
    // public void start2StopMusic(float x, float y) {
    // if (x < 80 && y > 760) {
    // System.out.println("是");
    // if (time <= 0) {
    // menu.reset();
    // }
    // } else if (x > 400 && y > 760) {
    // System.out.println("否");
    // if (time <= 0) {
    // menu.reset();
    // }
    // }
    // }
    public void paint(Canvas g, int canvasIndex) // 绘制
    {
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        switch (canvasIndex) {
            case CANVAS_PROGRESS:
                //白色画笔
                g.drawBitmap(BitmapFactory.decodeResource(res, R.drawable.bg_ff), null, new Rect(0, 0, 800, 480), mPaint);

//                g.drawColor(0xffacacac);
                mPaint.setColor(0xff000000);

                if (time > 0) {
                    //  g.drawRect(100, 790, 40 + (10 - time) * 20, 793, mPaint);
                    // 绘制进度
                    mPaint.setTextSize(30);

                    // 文字内容位置
                    g.drawText("加载中...", WIDTH / 2 - 37, HEIGHT / 2 - 15, mPaint);
                }

                // else {
                // paint.setTextSize(30);
                // g.drawText("是否开启声音", 150, 600, paint);
                // g.drawText("是", 10, 780, paint);
                // g.drawText("否", 440, 780, paint);
                // }
                break;
            case CANVAS_NPC_INTRODUCE:
                npcIntroduce.render(g, mPaint);
                break;
            case CANVAS_SMALL_DIALOG:
                smallDialog.render(g, mPaint);
                break;
            case CANVAS_BILLING_DIALOG:
                billingDialog.render(g, mPaint);
                break;
            case CANVAS_GET_GIFT:
                getGift.render(g, mPaint);
                break;
            case CANVAS_DAY_GIFT:
                dayGift.render(g, mPaint);
                break;
            case CANVAS_MENU:
                menu.render(g, mPaint);
                break;
            case CANVAS_HEFP:
                help.render(g, mPaint);
                break;
            case CANVAS_SETTING:
                setting.render(g, mPaint);
                break;
            // case CANVAS_LOADING:
            // load.render(g, paint);
            // break;
            case CANVAS_ACHIEVE:
                achieve.render(g, mPaint);
                break;
            case CANVAS_CHOOSE_BOSS:
                chooseBoss.render(g, mPaint);
                break;
            case CANVAS_CHOOSE_AIRPLANE:
                chooseAirplane.render(g, mPaint);
                break;
            case CANVAS_LEVEL:
                level.render(g, mPaint);
                break;
            case CANVAS_CHOOSE_BOSS_START:
                loading.render(g, mPaint);
                break;
            case CANVAS_AIRPLANE_UPGRADE:
                airplaneUpgrade.render(g, mPaint);
                break;
            case CANVAS_FIRST_STORY_LINE:
                storyLine.render(g, mPaint);
                break;
            case CANVAS_GAME:
                game.render(g, mPaint);
                // paint.setColor(0xffff0000) ;
                // paint.setTextSize(40) ;
                // g.drawText(" "+game.nm.zl.t, 10, 200, paint) ;
                break;
            case CANVAS_GAME_WIN:
                gameWin.render(g, mPaint);
                break;
            case CANVAS_GAME_PAUSE:
                pause.render(g, mPaint);
                break;
            case CANVAS_SKILL_BACKGROUND:
                biShaBg.render(g, mPaint);
                break;
            case CANVAS_GAME_EXIT:
                gameExit.render(g, mPaint);
                break;
        }
        // paint.setColor(0xffffffff) ;
        // paint.setTextSize(40) ;
        // g.drawText("触底  "+NPCManager.num, 10, 50, paint) ;
    }

    public void upData() {
        switch (canvasIndex) {
            case CANVAS_PROGRESS:
                if (time > 0) {
                    time--;
                    if (time == 8) {
                        Data.load();
                    } else if (time == 5) {
                        initMic();
                    } else if (time == 2) {
                        // isSound = PaymentJoy.isMusicon();
                        MainActivity.isYD = MainActivity.main.isCM();
                    } else if (time <= 0) {
                        if (Game.isFrist) {
                            storyLine.init(res);
                            storyLine.reset();
                        } else {
                            menu.init(res);
                            menu.reset();
                            isPlayMusic(bossMediaPlayer, gameMediaPlayer,
                                    menuMediaPlayer);
                        }
                    }
                }
                break;
            case CANVAS_NPC_INTRODUCE:
                npcIntroduce.upData();
                break;
            case CANVAS_SMALL_DIALOG:
                smallDialog.upData();
                break;
            case CANVAS_BILLING_DIALOG:
                billingDialog.upData();
                break;
            case CANVAS_GET_GIFT:
                getGift.upData();
                break;
            case CANVAS_DAY_GIFT:
                dayGift.upData();
                break;
            case CANVAS_MENU:
                menu.upData();
                break;
            case CANVAS_HEFP:
                help.upData();
                break;
            case CANVAS_SETTING:
                setting.upData();
                break;
            // case CANVAS_LOADING:
            // load.upData();
            // break;
            case CANVAS_ACHIEVE:
                achieve.upData();
                break;
            case CANVAS_CHOOSE_BOSS:
                chooseBoss.upData();
                break;
            case CANVAS_CHOOSE_AIRPLANE:
                chooseAirplane.upData();
                break;
            case CANVAS_LEVEL:
                level.upData();
                break;
            case CANVAS_CHOOSE_BOSS_START:
                loading.upData();
                break;
            case CANVAS_AIRPLANE_UPGRADE:
                airplaneUpgrade.upData();
                break;
            case CANVAS_FIRST_STORY_LINE:
                storyLine.upData();
                break;
            case CANVAS_GAME:
                game.upData();
                break;
            case CANVAS_GAME_WIN:
                gameWin.upData();
                break;
            case CANVAS_GAME_PAUSE:
                pause.upData();
                break;
            case CANVAS_SKILL_BACKGROUND:
                biShaBg.upData();
                break;
            case CANVAS_GAME_EXIT:
                gameExit.upData();
                break;
        }
    }

    public void run() // 更新
    {
        long startTime, endTime;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        while (isRun) {
            try {
                startTime = System.currentTimeMillis();
                // 更新
                upData();
                // 重绘
                Canvas g = mSurfaceHolder.lockCanvas();
                if (WIDTH != MainActivity.SCREEN_WIDTH
                        || HEIGHT != MainActivity.SCREEN_HEIGHT || isShake) {
                    if (!isShake) {
                        paint(mCanvas, canvasIndex);
                        g.drawBitmap(mBitmap, null, new Rect(0, 0,
                                MainActivity.SCREEN_WIDTH,
                                MainActivity.SCREEN_HEIGHT), paint);
                    } else {
                        int dx = 0;
                        if (shakeIndex == 0) {
                            dx = -8;
                        } else if (shakeIndex == 2) {
                            dx = 8;
                        }
                        shakeIndex++;
                        if (shakeIndex >= 3) {
                            shakeIndex = 0;
                        }
                        paint(mCanvas, canvasIndex);
                        g.drawBitmap(mBitmap, null, new Rect(dx, 0,
                                MainActivity.SCREEN_WIDTH + dx,
                                MainActivity.SCREEN_HEIGHT), paint);
                    }
                } else {
                    paint(g, canvasIndex);
                }

                mSurfaceHolder.unlockCanvasAndPost(g);
                endTime = System.currentTimeMillis();
                // 休眠
                if (endTime - startTime < WITE_TIME) {
                    Thread.sleep(WITE_TIME - (endTime - startTime));
                }
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                }
            }
        }
    }


    public boolean onTouchEvent(MotionEvent e) // 控制
    {
        float x = e.getX(), y = e.getY();
        if (WIDTH != MainActivity.SCREEN_WIDTH
                || HEIGHT != MainActivity.SCREEN_HEIGHT) {
            x = x * WIDTH / MainActivity.SCREEN_WIDTH;
            y = y * HEIGHT / MainActivity.SCREEN_HEIGHT;
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN) // 按下
        {
            // Log.d("HL", "-----    " + Player.hl) ;
            switch (canvasIndex) {
                case CANVAS_PROGRESS:
                    // start2StopMusic(x, y);
                    break;
                case CANVAS_NPC_INTRODUCE:
                    npcIntroduce.touchDown(x, y);
                    break;
                case CANVAS_BILLING_DIALOG:
                    billingDialog.touchDown(x, y);
                    break;
                case CANVAS_GET_GIFT:
                    getGift.touchDown(x, y);
                    break;
                case CANVAS_DAY_GIFT:
                    dayGift.touchDown(x, y);
                    break;
                case CANVAS_MENU:
                    menu.touchDown(x, y);
                    break;
                case CANVAS_HEFP:
                    help.touchDown(x, y);
                    break;
                case CANVAS_SETTING:
                    setting.touchDown(x, y);
                    break;
                case CANVAS_ACHIEVE:
                    achieve.touchDown(x, y);
                    break;
                case CANVAS_CHOOSE_BOSS:
                    chooseBoss.touchDown(x, y);
                    break;
                case CANVAS_CHOOSE_AIRPLANE:
                    chooseAirplane.touchDown(x, y);
                    break;
                case CANVAS_LEVEL:
                    level.touchDown(x, y);
                    break;
                case CANVAS_AIRPLANE_UPGRADE:
                    airplaneUpgrade.touchDown(x, y);
                    break;
                case CANVAS_FIRST_STORY_LINE:
                    storyLine.touchDown(x, y);
                    break;
                case CANVAS_GAME:
                    game.touchDown(x, y);
                    // Log.d("****  yy  ****",
                    // "----------  t  ----------    "+game.nm.zl.t) ;
                    break;
                case CANVAS_GAME_WIN:
                    gameWin.touchDown(x, y);
                    break;
                case CANVAS_GAME_PAUSE:
                    pause.touchDown(x, y);
                    break;
                case CANVAS_GAME_EXIT:
                    gameExit.touchDown(x, y);
                    break;
            }
        } else if (e.getAction() == MotionEvent.ACTION_UP)// 抬起
        {
            switch (canvasIndex) {
                case CANVAS_MENU:
                    menu.touchUp(x, y);
                    break;
                case CANVAS_ACHIEVE:
                    achieve.touchUp(x, y);
                    break;
                case CANVAS_CHOOSE_BOSS:
                    chooseBoss.touchUp(x, y);
                    break;
                case CANVAS_CHOOSE_AIRPLANE:
                    chooseAirplane.touchUp(x, y);
                    break;
                case CANVAS_LEVEL:
                    level.touchUp(x, y);
                    break;
                case CANVAS_GAME:
                    game.touchUp(x, y);
                    break;
                case CANVAS_GAME_EXIT:
                    gameExit.touchUp(x, y);
                    break;
                case CANVAS_SETTING:
                    setting.touchUp(x, y);
                case CANVAS_GAME_PAUSE:
                    pause.touchUp(x, y);
                    break;
                case CANVAS_GAME_WIN:
                    gameWin.touchUp(x, y);
                    break;
                case CANVAS_HEFP:
                    help.touchUp(x, y);
                    break;
                case CANVAS_AIRPLANE_UPGRADE:
                    airplaneUpgrade.touchUp(x, y);
                    break;
            }
        } else if (e.getAction() == MotionEvent.ACTION_MOVE)// 移动
        {
            switch (canvasIndex) {
                case CANVAS_MENU:
                    menu.touchMove(x, y);
                    break;
                case CANVAS_ACHIEVE:
                    achieve.touchMove(x, y);
                    break;
                case CANVAS_CHOOSE_BOSS:
                    chooseBoss.touchMove(x, y);
                    break;
                case CANVAS_CHOOSE_AIRPLANE:
                    chooseAirplane.touchMove(x, y);
                    break;
                case CANVAS_LEVEL:
                    level.touchMove(x, y);
                    break;
                case CANVAS_GAME:
                    game.touchMove(x, y);
                    break;
                case CANVAS_GAME_EXIT:
                    gameExit.touchMove(x, y);
                    break;
                case CANVAS_SETTING:
                    setting.touchMove(x, y);
                case CANVAS_GAME_PAUSE:
                    pause.touchMove(x, y);
                    break;
                case CANVAS_GAME_WIN:
                    gameWin.touchMove(x, y);
                    break;
                case CANVAS_HEFP:
                    help.touchMove(x, y);
                    break;
                case CANVAS_AIRPLANE_UPGRADE:
                    airplaneUpgrade.touchMove(x, y);
                    break;
            }
        }
        return true;
    }

    public boolean keyDown(int k) {

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
                switch (canvasIndex) {
                    case CANVAS_GAME_EXIT:
                        gameExit.touchDown(200, 500);
                        break;
                }
                break;
            case KeyEvent.KEYCODE_BACK://返回
                Log.e("jamie", "－－－－－返回－－－－－");
                switch (canvasIndex) {
                    case CANVAS_GET_GIFT:
                        getGift.touchDown(400, 240);
                        break;
                    case CANVAS_DAY_GIFT:
                        dayGift.touchDown(240, 700);
                        break;
                    case CANVAS_MENU:
                        menu.touchDown(-100, -100);
                        break;
                    case CANVAS_GAME:
                        game.touchDown(450, Game.GG + 20);
                        game.touchUp(450, Game.GG + 20);
                        break;
                    case CANVAS_GAME_PAUSE:
                        pause.touchDown(240, 200);
                        pause.touchUp(240, 200);
                        break;
                }
                return true;
            case KeyEvent.KEYCODE_HOME://房子
                Log.e("jamie", "－－－－－房子－－－－－");
                break;
            case KeyEvent.KEYCODE_MENU://菜单
                Log.e("jamie", "－－－－－菜单－－－－－");
                break;
        }
        return false;
    }

    public static AudioManager mgr;
    public static SoundPool spool;
    public static int[] soundID = new int[16];

    public void initMic() {
        mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        menuMediaPlayer = MediaPlayer.create(MainActivity.main, R.raw.menu);
        menuMediaPlayer.setLooping(true);
        gameMediaPlayer = MediaPlayer.create(MainActivity.main, R.raw.game);
        gameMediaPlayer.setLooping(true);
        bossMediaPlayer = MediaPlayer.create(MainActivity.main, R.raw.boss);
        bossMediaPlayer.setLooping(true);

        spool = new SoundPool(6, AudioManager.STREAM_MUSIC, 100);
        soundID[0] = GameDraw.spool.load(MainActivity.main, R.raw.bao1, 1);
        soundID[1] = GameDraw.spool.load(MainActivity.main, R.raw.bao2, 1);

        soundID[2] = GameDraw.spool.load(MainActivity.main, R.raw.dianan, 1);

        soundID[3] = GameDraw.spool.load(MainActivity.main, R.raw.kaimen, 1);

        // soundID[4] = GameDraw.spool.load(MainActivity.main, R.raw.dianliu,
        // 1);
        //
        // soundID[5] = GameDraw.spool.load(MainActivity.main, R.raw.pzidan, 1);

        soundID[6] = GameDraw.spool.load(MainActivity.main, R.raw.chishuijing,
                1);

        soundID[7] = GameDraw.spool.load(MainActivity.main, R.raw.chidaoju, 1);

        soundID[8] = GameDraw.spool.load(MainActivity.main, R.raw.jineng, 1);

        soundID[9] = GameDraw.spool.load(MainActivity.main, R.raw.bossbianshen,
                1);

        soundID[10] = GameDraw.spool.load(MainActivity.main, R.raw.bs, 1);
        soundID[11] = GameDraw.spool.load(MainActivity.main, R.raw.npcbao2, 1);
        soundID[12] = GameDraw.spool.load(MainActivity.main, R.raw.npcbao3, 1);
        soundID[13] = GameDraw.spool.load(MainActivity.main, R.raw.zdbao1, 1);
        soundID[14] = GameDraw.spool.load(MainActivity.main, R.raw.zdbao2, 1);

        soundID[15] = GameDraw.spool
                .load(MainActivity.main, R.raw.explode01, 1);
    }

    public static void playSound(int id) {
        if (GameDraw.isSound && id >= 0 && id < soundID.length) {
            int streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
            spool.play(soundID[id], streamVolume, streamVolume, 1, 0, 1);
        }
    }

    public static void gameSound(int id) {
        switch (id) {
            case 0:
                // playSound(Math.abs(MC.ran.nextInt())%2) ;
                switch (Math.abs(GameDraw.random.nextInt()) % 7) {
                    case 0:
                        playSound(0);
                        break;
                    case 1:
                        playSound(1);
                        break;
                    case 2:
                        playSound(10);
                        break;
                    case 3:
                        playSound(11);
                        break;
                    case 4:
                        playSound(12);
                        break;
                    case 5:
                        playSound(13);
                        break;
                    case 6:
                        playSound(14);
                        break;
                }
                break;
            case 1:
                playSound(2);
                break;
            case 2:
                playSound(3);
                break;
            // case 3:
            // playSound(4);
            // break;
            // case 4:
            // playSound(5);
            // break;
            case 5:
                playSound(6);
                break;
            case 6:
                playSound(7);
                break;
            case 7:
                playSound(8);
                break;
            case 8:
                playSound(9);
                break;
            case 9:
                playSound(15);
                break;
        }
    }

    public static void isPlayMusic(MediaPlayer m1, MediaPlayer m2,
                                   MediaPlayer m3) {
        if (m1.isPlaying()) {
            m1.pause();
        }
        if (m2.isPlaying()) {
            m2.pause();
        }
        if (isSound) {
            m3.start();
        } else {
            if (m3.isPlaying()) {
                m3.pause();
            }
        }
    }
}
