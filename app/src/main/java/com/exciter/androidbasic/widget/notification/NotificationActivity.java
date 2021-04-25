package com.exciter.androidbasic.widget.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RemoteViews;

import com.exciter.androidbasic.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static android.app.Notification.VISIBILITY_SECRET;

public class NotificationActivity extends AppCompatActivity {

    private RadioGroup mRgLockVisibleLevel;
    private RadioGroup mRgNotifyLevel;
    private NotificationManager mNotificationManager;
    private String mNotificationTitle = "";
    private String mNotificationText = "";
    private static final String NOTIFICATION_CHANNEL_ID = "channel_id";
    private static final String NOTIFICATION_CHANNEL_NAME = "channel_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("通知");
        toolbar.setNavigationOnClickListener( v -> finish());
        mRgLockVisibleLevel = findViewById(R.id.rg_lock_level);
        mRgNotifyLevel = findViewById(R.id.rg_notify_level);
        findViewById(R.id.btn_01).setOnClickListener(v -> createNotification01());
        findViewById(R.id.btn_02).setOnClickListener(v -> createNotification02());
        findViewById(R.id.btn_03).setOnClickListener(v -> createNotification03());
        findViewById(R.id.btn_04).setOnClickListener(v -> createNotification04());
        findViewById(R.id.btn_05).setOnClickListener(v -> createNotification05());
        findViewById(R.id.btn_06).setOnClickListener(v -> createNotification06());
        findViewById(R.id.btn_07).setOnClickListener(v -> createNotification07());
        findViewById(R.id.btn_08).setOnClickListener(v -> createNotification08());
        findViewById(R.id.btn_09).setOnClickListener(v -> createNotification09());
    }

    /**
     * 创建普通通知：点击跳转到网页
     */
    private void createNotification01() {
        mNotificationTitle = "普通通知";
        mNotificationText = "这是普通通知的内容这是普通通知的内容这是普通通知的内容这是普通通知的内容这是普通通知的内容这是普通通知的内容";
        NotificationCompat.Builder builder = getNotificationBuilder();
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_android));
        getNotificationManager().notify(1, builder.build());
    }

    /**
     * 创建带操作按钮的通知
     */
    private void createNotification02() {
        mNotificationTitle = "带操作按钮的通知";
        mNotificationText = "点击跳转到百度搜索点击跳转到百度搜索点击跳转到百度搜索点击跳转到百度搜索点击跳转到百度搜索点击跳转到百度搜索";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.so.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = getNotificationBuilder();
        builder.addAction(R.drawable.ic_android, "跳转到360搜索", pendingIntent);
        getNotificationManager().notify(2, builder.build());
    }


    /**
     * 创建带进度条的通知
     */
    private void createNotification03() {
        mNotificationTitle = "带进度条的通知";
        mNotificationText = "下载进度：0%";
        int progressMax = 100;
        int progressCurrent = 0;
        NotificationCompat.Builder builder = getNotificationBuilder();
        builder.setProgress(progressMax, progressCurrent, false);
        getNotificationManager().notify(3, builder.build());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            int progressCurrent = 0;

            @Override
            public void run() {
                if (progressCurrent >= progressMax) {
                    scheduledExecutorService.shutdown();
                    builder.setProgress(progressMax, progressMax, false)
                            .setContentText("下载完成：" + progressMax + "%");
                } else {
                    builder.setProgress(progressMax, progressCurrent += 5, false)
                            .setContentText("下载进度：" + progressCurrent + "%");
                }
                getNotificationManager().notify(3, builder.build());
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void createNotification04() {
        mNotificationTitle = "大图片通知";
        mNotificationText = "这是大图片通知这是大图片通知这是大图片通知这是大图片通知这是大图片通知这是大图片通知这是大图片通知";
        NotificationCompat.Builder builder = getNotificationBuilder();
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.card)));
        getNotificationManager().notify(4, builder.build());
    }

    private void createNotification05() {
        mNotificationTitle = "大文本通知";
        mNotificationText = "大文本通知大文本通知大文本通知大文本通知大文本通知大文本通知大文本通知大文本通知大文本通知";
        NotificationCompat.Builder builder = getNotificationBuilder();
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("这是大文本内容这是大文本内容这是大文本内容这是大文本内容这是大文本" +
                "内容这是大文本内容这是大文本内容这是大文本内容这是大文本内容本内容这是大文本内容这是大文本内容这是大文本内容本内容这是大文本内容这是大文本内容这是大文本内容" +
                "大文本内容这是大文本内容本内容这是大文本内容这是大文本内容这是大文本内容大文本内容这是大文本内容本内容这是大文本内容这是大文本内容这是大文本内容" +
                "大文本内容这是大文本内容本内容这是大文本内容这是大文本内容这是大文本内容大文本内容这是大文本内容本内容这是大文本内容这是大文本内容这是大文本内容" +
                "大文本内容这是大文本内容本内容这是大文本内容这是大文本内容这是大文本内容大文本内容这是大文本内容本内容这是大文本内容这是大文本内容这是大文本内容"));
        getNotificationManager().notify(5, builder.build());
    }

    private void createNotification06() {
        mNotificationTitle = "收件箱通知";
        mNotificationText = "收件箱通知收件箱通知收件箱通知收件箱通知收件箱通知收件箱通知收件箱通知收件箱通知收件箱通知";
        NotificationCompat.Builder builder = getNotificationBuilder();
        builder.setStyle(new NotificationCompat.InboxStyle()
                .addLine("快讯1：日本政府不顾国际社会反对，将福岛核废水排入太平洋")
                .addLine("快讯2：拜登宣布美军将尽快从阿富汗撤军")
                .addLine("快讯3：我国疫苗接种已超过2亿人次，位居世界首位")
                .addLine("快讯4：五一小长假一票难求，旅游业已恢复至疫情前状态")
                .addLine("快讯5：我国空间站已准备就绪，将于近期开始发射")
                .addLine("快讯6：特斯拉就上海车展女子维权事件道歉")
                .addLine("快讯7：中芯国际7nm制程将于今年量产")
                .addLine("快讯8：蔡英文表示，如果大陆梧桐，自己将会提前移民美国")
                .addLine("快讯9：我国火星探测器天文1号成功登录火星，火星车成功释放"));
        getNotificationManager().notify(6, builder.build());
    }

    private void createNotification07() {
        mNotificationTitle = "自定义通知";
        mNotificationText = "自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知";
        NotificationCompat.Builder builder = getNotificationBuilder();
        RemoteViews customLayout = new RemoteViews(getPackageName(), R.layout.view_custom_notification);
        builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(customLayout);
        getNotificationManager().notify(7, builder.build());
    }

    private void createNotification08() {
        mNotificationTitle = "自定义通知";
        mNotificationText = "自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知";
        NotificationCompat.Builder builder = getNotificationBuilder();
        RemoteViews customBigLayout = new RemoteViews(getPackageName(), R.layout.view_custom_big_notification);
        builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomBigContentView(customBigLayout);
        getNotificationManager().notify(8, builder.build());
    }

    private void createNotification09() {
        mNotificationTitle = "自定义通知";
        mNotificationText = "自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知自定义通知";
        NotificationCompat.Builder builder = getNotificationBuilder();
        RemoteViews customLayout = new RemoteViews(getPackageName(), R.layout.view_custom_notification);
        RemoteViews customBigLayout = new RemoteViews(getPackageName(), R.layout.view_custom_big_notification);
        builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(customLayout)
                .setCustomBigContentView(customBigLayout);
        getNotificationManager().notify(8, builder.build());
    }

    private NotificationManager getNotificationManager() {
        if (null == mNotificationManager) {
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle(mNotificationTitle)
                .setContentText(mNotificationText)
                .setAutoCancel(true);
        setNotificationNotifyLevel(builder);
        setNotificationLockVisibleLevel(builder);
        return builder;
    }

    /**
     * 创建渠道：Android8.0之后版本需要设置channel
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            //是否绕过请勿打扰模式
            channel.canBypassDnd();
            //设置可绕过 请勿打扰模式
            channel.setBypassDnd(true);
            //锁屏显示
            channel.setLockscreenVisibility(VISIBILITY_SECRET);
            //闪光灯
            channel.enableLights(true);
            //闪光灯颜色
            channel.setLightColor(Color.RED);
            //设置是否有灯光
            channel.shouldShowLights();
            //左面launcher的消息角标
            channel.canShowBadge();
            //是否允许震动
            channel.enableVibration(false);
            //设置震动模式
            channel.setVibrationPattern(new long[]{100, 100, 200});
            //获取系统通知响铃声音的配置
            channel.getAudioAttributes();
            //获取通知渠道组
            channel.getGroup();
            getNotificationManager().createNotificationChannel(channel);
        }
    }

    /**
     * 设置锁屏显示级别
     *
     * @param builder NotificationCompat.Builder
     */
    @SuppressLint("NonConstantResourceId")
    private void setNotificationNotifyLevel(NotificationCompat.Builder builder) {
        switch (mRgLockVisibleLevel.getCheckedRadioButtonId()) {
            case R.id.rb_max:
                builder.setPriority(NotificationCompat.PRIORITY_MAX);
                break;
            case R.id.rb_high:
                builder.setPriority(NotificationCompat.PRIORITY_HIGH);
                break;
            case R.id.rb_default:
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                break;
            case R.id.rb_low:
                builder.setPriority(NotificationCompat.PRIORITY_LOW);
                break;
            case R.id.rb_min:
                builder.setPriority(NotificationCompat.PRIORITY_MIN);
                break;
            default:
                break;
        }
    }

    /**
     * 设置通知级别
     *
     * @param builder NotificationCompat.Builder
     */
    @SuppressLint("NonConstantResourceId")
    private void setNotificationLockVisibleLevel(NotificationCompat.Builder builder) {
        switch (mRgNotifyLevel.getCheckedRadioButtonId()) {
            case R.id.rb_public:
                //显示通知的完整内容
                builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                break;
            case R.id.rb_private:
                //显示基本信息，例如通知图标和内容标题，但隐藏通知的完整内容
                builder.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);
                break;
            case R.id.rb_secret:
                //不在锁定屏幕上显示该通知的任何部分
                builder.setVisibility(NotificationCompat.VISIBILITY_SECRET);
                break;
            default:
                break;

        }
    }
}