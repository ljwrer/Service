package com.example.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{
    private Button start;
    private Button stop;
    private Button bind;
    private Button unBind;
    private Button startIntentService;
    private MyService.MyDownloadBinder binder;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder=(MyService.MyDownloadBinder)service;
            binder.startDownload();
            binder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button)findViewById(R.id.start_service);
        stop=(Button)findViewById(R.id.stop_service);
        bind=(Button)findViewById(R.id.bind_service);
        unBind=(Button)findViewById(R.id.unbind_service);
        startIntentService=(Button)findViewById(R.id.start_IntentService);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unBind.setOnClickListener(this);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.start_service:
                Intent startIntent=new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent=new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent=new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                    unbindService(connection);
                break;
            case R.id.start_IntentService:
                Log.d(MyIntentService.TAG2,"Thread id is "+Thread.currentThread().getId());
                Intent startIntentService=new Intent(this,MyIntentService.class);
                startService(startIntentService);
                break;
            default:
                break;
        }
    }

}
