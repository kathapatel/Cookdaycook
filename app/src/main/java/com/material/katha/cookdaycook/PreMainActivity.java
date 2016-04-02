package com.material.katha.cookdaycook;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;

import com.facebook.FacebookSdk;

/**
 * Created by rajiv on 3/9/2016.
 */
public class PreMainActivity extends Activity {
    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premain);

        prefs = getSharedPreferences("com.material.katha.cookdaycook", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    final Intent i= new Intent(getBaseContext(),tutorial.class);
                    PreMainActivity.this.startActivity(i);
                    PreMainActivity.this.finish();
                }
            },2000);
            prefs.edit().putBoolean("firstrun", false).commit();
        }
        else{
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    final Intent i = new Intent(getBaseContext(), LoginActivity.class);
                    PreMainActivity.this.startActivity(i);
                    PreMainActivity.this.finish();
                }
            }, 2000);
        }
    }

}
