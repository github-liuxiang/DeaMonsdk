package sdk.deamon.polylin.deamonsdk_demo;

import android.app.IntentService;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.JobIntentService;
import android.util.Log;

import polylink.sdk.pl.c.service.BaseIntentService;
import polylink.sdk.pl.c.service.BaseService;

/**
 * Created by Liuxiang on 2018/11/29.
 */

public class WeTalkService extends Service {




    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(WeTalkService.this,GuardService.class));
        startService(new Intent(WeTalkService.this,GuardService2.class));

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("liuxiangw","onDestroy");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
