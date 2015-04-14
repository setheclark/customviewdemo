package com.scdev.viewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.scdev.viewdemo.lib.CustomView;


public class MainActivity extends Activity {

    CustomView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (CustomView) findViewById(R.id.cv);

        view.setMainLabelText("Test Text");
        view.setIsLoading(true);

        if (savedInstanceState == null) {
            loadViews();
        }


    }

    private void loadViews() {
        new CountDownTimer(2000, 2000) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                view.setIsLoading(false);
                view.setSecondaryLabelText("Finished");
            }
        }.start();
    }

}