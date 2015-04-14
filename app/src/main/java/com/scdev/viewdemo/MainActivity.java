package com.scdev.viewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.scdev.viewdemo.lib.CustomView;


public class MainActivity extends Activity {

    CustomView view;
    CustomView view1;
    CustomView view2;
    CustomView view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (CustomView) findViewById(R.id.cv);
        view1 = (CustomView) findViewById(R.id.cv1);
        view2 = (CustomView) findViewById(R.id.cv2);
        view3 = (CustomView) findViewById(R.id.cv3);

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

                view1.setIsLoading(false);
                view1.setViewState(CustomView.ViewState.NORMAL);
                view1.setSecondaryLabelText("Normal");

                view2.setIsLoading(false);
                view2.setViewState(CustomView.ViewState.WARNING);
                view2.setSecondaryLabelText("Warning");

                view3.setIsLoading(false);
                view3.setViewState(CustomView.ViewState.ERROR);
                view3.setSecondaryLabelText("Error");
            }
        }.start();
    }

}