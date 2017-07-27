package gam.org.com.leidianzhanji;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lutongnet.analytics.LTGameAgent;
import com.lutongnet.pay.jiangsu.mobile.interf.IPayCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import gam.org.com.leidianzhanji.play.Airplane;
import gam.org.com.leidianzhanji.play.AirplaneUpgrade;
import gam.org.com.leidianzhanji.play.Data;
import gam.org.com.leidianzhanji.play.Game;
import gam.org.com.leidianzhanji.play.GameDraw;

import static gam.org.com.leidianzhanji.play.AirplaneUpgrade.MODE_DONG;
import static gam.org.com.leidianzhanji.play.AirplaneUpgrade.MODE_JING;

public class MainActivity extends Activity {
    public static boolean sysDebug = true;
    public static boolean gcDebug = true;
    public static boolean isFirstPlay = true;// 用于判断是否是第一次玩
    public static boolean isEndPlay = false;// 用于判断是否通关
    public static final boolean isShowBuyMessage = true;

    public static int SCREEN_WIDTH, SCREEN_HEIGHT;
    public static MainActivity main;
    GameDraw gameDraw;

    public MainActivity() {
        main = this;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        EventBus.getDefault().register(this);
        LTGameAgent.setDebugMode(true);// 设置输出运⾏行行时⽇日志
        LTGameAgent.init(this);
//findViewById(R.id.tv_button).setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        EventBus.getDefault().post(new EventMessage(EventMessage.TAG1));
//    }
//});
        // 去掉标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去掉状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;

        Log.e("jamie", SCREEN_WIDTH + "－－－－－界面宽高－－－－－" + SCREEN_HEIGHT);

        gameDraw = new GameDraw(this);
        gameDraw.setKeepScreenOn(true);
        setContentView(gameDraw);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(final EventMessage event) {
        Log.e("11111","----------onMessageEvent---------");
        String payCode = "";
        switch (event.getTag()) {
            case EventMessage.TAG1: {//
                Log.e("购买","发送擎天柱Code");
                payCode = "201707170002001";
                break;
            }
            case EventMessage.TAG2: {//
                payCode = "201707170002001";
                break;
            }
            case EventMessage.TAG3: {//
                payCode = "201707170002001";
                break;
            }
            case EventMessage.TAG4: {//
                payCode = "201707170002001";
                break;
            }
            case EventMessage.TAG5: {//
                payCode = "201707170002001";
                break;
            }
            case EventMessage.TAG6: {//
                payCode = "201707170002001";
                break;
            }
            case EventMessage.TAG7: {//
                payCode = "201707170002001";
                break;
            }
        }

        LTGameAgent.payment(payCode, new IPayCallback() {
            @Override
            public void onPayResult(int i, final Map<String, String> map) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch (event.getTag()) {
                            case EventMessage.TAG1: {//  擎天柱
                                Log.e("购买","擎天柱成功");
                                gameDraw.chooseAirplane.buyID = 3;
                                break;
                            }
                            case EventMessage.TAG2: {//  水晶石
                                int sj = event.getSj();
                                switch (sj) {
                                    case 1:
                                        gameDraw.game.addShuijing(5000);
                                        Data.save();
                                        gameDraw.airplaneUpgrade.mode = MODE_DONG;
                                        break;
                                    case 2:
                                        gameDraw.game.addShuijing(11000);
                                        Data.save();
                                        gameDraw.airplaneUpgrade.mode = MODE_DONG;
                                        break;
                                    case 3:
                                        gameDraw.game.addShuijing(20000);
                                        Data.save();
                                        gameDraw.airplaneUpgrade.mode = MODE_DONG;
                                        break;
                                }
                                break;
                            }
                            case EventMessage.TAG3: {//  一键满级
                                // dj[1] = 5 ; dj[2] = 5 ; dj[3] = 5 ; dj[4] = 5 ;// dj[5] = 5 ;
                                AirplaneUpgrade.dj[gameDraw.airplaneUpgrade.id] = 5;
                                gameDraw.airplaneUpgrade.chackRY();
                                gameDraw.airplaneUpgrade.mode = MODE_JING;
                                gameDraw.airplaneUpgrade.t = 0;
                                break;
                            }
                            case EventMessage.TAG4: {//  购买必杀
                                Game.bisha += 4;
                                Data.save();
                                break;
                            }
                            case EventMessage.TAG5: {//  购买保护
                                Game.baohu += 4;
                                Data.save();
                                break;
                            }
                            case EventMessage.TAG6: {//  立即复活
                                Game.sm = 1;
                                gameDraw.game.airplane.createPlayer();
                                Airplane.fh = true;
                                Data.save();
                                break;
                            }
                            case EventMessage.TAG7: {//  战斗礼包
                                Game.bisha += 5;
                                Game.baohu += 5;
                                gameDraw.game.addShuijing(5000);
                                Data.save();
                                break;
                            }
                        }
                    }
                });
            }
        });
    }

    public boolean onKeyDown(int key, KeyEvent keyEvent) {
        if (gameDraw.keyDown(key) == true) {
            return true;
        } else {
            return super.onKeyDown(key, keyEvent);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        Log.e("onKeyUp","----------1--------");
        if (gameDraw.keyUp(keyCode) == true) {
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void onPause() {
        super.onPause();

        LTGameAgent.onPause();
        if (GameDraw.isSound) {
            if (GameDraw.gameMediaPlayer.isPlaying()) {
                GameDraw.gameMediaPlayer.pause();
            }
            if (GameDraw.bossMediaPlayer.isPlaying()) {
                GameDraw.bossMediaPlayer.pause();
            }
            if (GameDraw.menuMediaPlayer.isPlaying()) {
                GameDraw.menuMediaPlayer.pause();
            }
        }
        switch (gameDraw.canvasIndex) {
            case GameDraw.CANVAS_GAME:
                // if(!isAppForeground){//从后台切到前台，打开91SDK暂停页
                // // TODO Auto-generated method stub
                // //调用pause方法
                // mc.gm.reset() ;
                // isAppForeground = true;
                // }
                gameDraw.pause.reset();
                gameDraw.pause.mode = 1;
                gameDraw.pause.t = 0;
                break;
        }

        // if(mc.canvasIndex != 20 && mc.canvasIndex != 25)
        // {
        // if(!isAppForeground){//从后台切到前台，打开91SDK暂停页
        // // TODO Auto-generated method stub
        // //调用pause方法
        // PaymentJoy.pause(this) ;
        // isAppForeground = true;
        // }
        // }
    }

    public void onResume() {
        super.onResume();

        LTGameAgent.onResume();

        if (GameDraw.isSound) {
            if (gameDraw.canvasIndex == GameDraw.CANVAS_GAME
                    || gameDraw.canvasIndex == GameDraw.CANVAS_GAME_PAUSE) {
                if (Game.bosst > 0) {
                    GameDraw.isPlayMusic(GameDraw.menuMediaPlayer,
                            GameDraw.gameMediaPlayer, GameDraw.bossMediaPlayer);
                } else {
                    GameDraw.isPlayMusic(GameDraw.menuMediaPlayer,
                            GameDraw.bossMediaPlayer, GameDraw.gameMediaPlayer);
                }
            } else if (gameDraw.canvasIndex != GameDraw.CANVAS_PROGRESS
                    && gameDraw.canvasIndex != GameDraw.CANVAS_FIRST_STORY_LINE) {
                GameDraw.isPlayMusic(GameDraw.gameMediaPlayer,
                        GameDraw.bossMediaPlayer, GameDraw.menuMediaPlayer);
            }
        }

        if (!isAppForeground) {// 从后台切到前台，打开91SDK暂停页
            // TODO Auto-generated method stub
            // 调用pause方法
//			PaymentJoy.pause(this);
            isAppForeground = true;
        }
    }

    protected void onStart() {
        super.onStart();
//		PaymentJoy.onStart();
    }

    protected void onStop() {
        super.onStop();
        if (!isAppOnForeground()) {// app进入后台
            isAppForeground = false;
        }
//		PaymentJoy.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onDestroy() {
        super.onDestroy();
        // PaymentJoy.onDestroy();
        System.exit(0);
    }

    public void PaymentResult(int resultCode, String[] cbParam) {

    }

    // 判断是否是移动的卡
    public static boolean isYD = true;

    public boolean isCM() {
        return true;
        // TelephonyManager manager = (TelephonyManager)
        // (getSystemService(Context.TELEPHONY_SERVICE));
        // String operator = manager.getSimOperator();
        // String opName = manager.getSimOperatorName();
        // String imsi = manager.getSubscriberId();
        // if (imsi.startsWith("46000") || imsi.startsWith("46002") ||
        // opName.equals("中国移动")||imsi.startsWith("46007"))
        // {
        // return true;
        // }else{
        // return false;
        // }
    }

    private static boolean isAppForeground = true;

    public static boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) main
                .getApplicationContext().getSystemService(
                        Context.ACTIVITY_SERVICE);
        String packageName = main.getApplicationContext().getPackageName();
        List<RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }
}