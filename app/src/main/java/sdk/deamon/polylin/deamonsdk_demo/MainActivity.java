package sdk.deamon.polylin.deamonsdk_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import polylink.sdk.pl.DeamonSdkCtrl;
import polylink.sdk.pl.c.receiver.PLReceiver;
import polylink.sdk.pl.c.receiver.PLReceiver_h;
import polylink.sdk.pl.c.service.BaseService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startService(new Intent(MainActivity.this,GuardService.class));
//        Intent workIntent = new Intent();
//        WeTalkService.enqueueWork(this,workIntent);
        DeamonSdkCtrl.getInstance().setProcess1("com.sdk.sip",
                WeTalkService.class.getCanonicalName(),
                PLReceiver.class.getCanonicalName()).setProcess2("sdk.deamon.polylin.deamonsdk_demo:pushservice",
                BaseService.class.getCanonicalName(),
                PLReceiver_h.class.getCanonicalName()).Init(this);
//        startService(new Intent(this,WeTalkService.class));
    }
}
