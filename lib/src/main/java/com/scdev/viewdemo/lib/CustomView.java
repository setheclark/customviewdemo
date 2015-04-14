package com.scdev.viewdemo.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CustomView extends LinearLayout {

    TextView mMainLabel;
    TextView mSecondaryLabel;
    ProgressBar mProgressBar;

    private boolean mIsLoading;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.custom_view, this);

        mMainLabel = (TextView) findViewById(R.id.__cv_main_label);
        mSecondaryLabel = (TextView) findViewById(R.id.__cv_secondary_label);
        mProgressBar = (ProgressBar) findViewById(R.id.__cv_progress);

    }

    public void setMainLabelText(int text) {
        mMainLabel.setText(text);
    }

    public void setMainLabelText(CharSequence text) {
        mMainLabel.setText(text);
    }

    public void setSecondaryLabelText(int text) {
        mSecondaryLabel.setText(text);
    }

    public void setSecondaryLabelText(CharSequence text) {
        mSecondaryLabel.setText(text);
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading = isLoading;

        mProgressBar.setVisibility(mIsLoading ? View.VISIBLE : View.INVISIBLE);
        mSecondaryLabel.setVisibility(mIsLoading ? View.INVISIBLE : View.VISIBLE);
    }
}
