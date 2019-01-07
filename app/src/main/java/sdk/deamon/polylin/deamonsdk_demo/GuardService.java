package sdk.deamon.polylin.deamonsdk_demo;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.JobIntentService;
import android.util.Log;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import polylink.sdk.pl.c.service.BaseIntentService;

public class GuardService extends Service {
    private Parcel mServiceData;
    private IBinder mRemote;
    private boolean iscon=false;



    @Override
    public void onCreate() {
        super.onCreate();

    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, new Notification());
        //绑定建立链接
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (!iscon){
                    bindService(new Intent(GuardService.this, GuardService2.class), mServiceConnection, Context.BIND_IMPORTANT);
                }
            }
        }.start();

        return START_STICKY;
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //链接上
            iscon=true;
            Log.e("liuxiangw","链接");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //断开链接
            Log.e("liuxiangw","断开链接");
            Intent workIntent = new Intent();
            WeTalkService.enqueueWork(GuardService.this,workIntent);
            Log.e("liuxiangw","断开链接2");
            //重新绑定

        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("liuxiangw","onDestroy");
        unbindService(mServiceConnection);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterface.Stub() {
            @Override
            public void onDeamon() throws RemoteException {

            }
        };
    }
}

