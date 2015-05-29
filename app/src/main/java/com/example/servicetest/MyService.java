package com.example.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.File;

/**
 * Created by Ray on 2015/5/26.
 */
public class MyService extends Service {
    public static final String TAG="MyService";
    MyDownloadBinder binder=new MyDownloadBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Notification notification=new Notification(R.drawable.icon,"Notification comes",System.currentTimeMillis());
//        Intent notificationIntent=new Intent(this,MainActivity.class);
//        PendingIntent pi=PendingIntent.getActivity(this,0,notificationIntent,0);
//        notification.setLatestEventInfo(this,"This is a title","this is content",pi);
//        startForeground(1,notification);
//        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder=new Notification.Builder(this);
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this, 0, intent, 0);
        builder.setSmallIcon(R.drawable.icon).setTicker("this is ticker text").setWhen(System.currentTimeMillis())
                .setContentTitle("This is content title").setContentText("This is a content text").setContentIntent(pi)
                .setAutoCancel(true);
        Notification notification=builder.build();
        Uri soundUri=Uri.fromFile(new File("/system/media/audio/notifications/Twinkle.ogg"));
        notification.sound=soundUri;
        long []vibrates={0,1000,1000,1000};
        notification.vibrate=vibrates;
        notification.ledARGB= Color.BLUE;
        notification.ledOnMS=1000;
        notification.ledOffMS=1000;
        notification.flags=Notification.FLAG_SHOW_LIGHTS;
//                notification.defaults=Notification.DEFAULT_ALL;
        startForeground(1,notification);
        Log.d(TAG,"onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }
    class MyDownloadBinder extends Binder {
        public void startDownload(){
            Log.d(TAG,"startDownLoad executed");
        }
        public void getProgress(){
            Log.d(TAG,"getProgress executed");
        }
    }
}
