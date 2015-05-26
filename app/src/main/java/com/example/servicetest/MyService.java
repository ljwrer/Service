package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

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
