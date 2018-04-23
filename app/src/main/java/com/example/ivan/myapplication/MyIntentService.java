package com.example.ivan.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import java.util.ResourceBundle;


public class MyIntentService extends IntentService {

    public static final String BD_MASSAGE_ID = "br_massage_id";
    public static final String KEY_FOR_EXTRA = "key_for_extra";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String s;

        if (intent.hasExtra("bundle_1")) {
            s  = intent.getBundleExtra("bundle_1")
                    .getString("bundle_key");
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras " + "bundle_1");
        }


        //Bundle bundle = intent.getBundleExtra("bunlde_1");

        s += "; data from Service";

        Intent intentToStartBroadcast = new Intent(BD_MASSAGE_ID);
        intentToStartBroadcast.putExtra(KEY_FOR_EXTRA,s);


        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        localBroadcastManager.sendBroadcast(intentToStartBroadcast);

       // Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    }
}
