package com.example.ivan.myapplication;

import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<String> {


    TextView mTextView;
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String s = intent.getStringExtra(MyIntentService.KEY_FOR_EXTRA);

            Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();

            mTextView.append(s);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mBroadcastReceiver,new IntentFilter(MyIntentService.BD_MASSAGE_ID));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    //IntentService
    public void button2OnClickHandler(View view) {
        Intent intent = new Intent(this,MyIntentService.class);

        Bundle bundle = new Bundle();
        bundle.putString("bundle_key","data from onClickHandler");

        intent.putExtra("bundle_1",bundle);
        startService(intent);
    }







    //==AsyncTaskLoader

    public void button1OnClickHandler(View view) {

        Bundle bundle = new Bundle();
        bundle.putString("bundle_key","bunle_value");

        getSupportLoaderManager().initLoader(0,bundle,this).forceLoad();
    }


    @Override
    public android.support.v4.content.Loader<String> onCreateLoader(int id, Bundle args) {
        return new myAsyncTaskLoader(this, args);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<String> loader, String data) {

        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<String> loader) {
    }

    public static class myAsyncTaskLoader extends AsyncTaskLoader<String> {

        String s;

        public myAsyncTaskLoader(Context context, Bundle args) {
            super(context);

            if (args != null) {
            }
            s = args.getString("bundle_key");
        }

        @Override
        public String loadInBackground() {
            s += "+ string from background";
            return s;
        }
    }

    }

