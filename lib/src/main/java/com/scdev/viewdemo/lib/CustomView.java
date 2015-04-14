package com.scdev.viewdemo.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
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

    public static final int SINGLE = 0;
    public static final int TOP = 1;
    public static final int MIDDLE = 2;
    public static final int BOTTOM = 3;

    private int mPosition = 0;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.custom_view, this);

        mMainLabel = (TextView) findViewById(R.id.__cv_main_label);
        mSecondaryLabel = (TextView) findViewById(R.id.__cv_secondary_label);
        mProgressBar = (ProgressBar) findViewById(R.id.__cv_progress);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomView, 0, 0);

        setPosition(a.getInt(R.styleable.CustomView_position, mPosition));
        mMainLabel.setText(a.getString(R.styleable.CustomView_labelText));
        setIsLoading(a.getBoolean(R.styleable.CustomView_isLoading, false));

        a.recycle();
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

    public void setPosition(int i) {
        mPosition = i;
        switch (mPosition) {
            case SINGLE:
                setBackgroundResource(R.drawable.single_bg);
                break;
            case TOP:
                setBackgroundResource(R.drawable.top_bg);
                break;
            case MIDDLE:
                setBackgroundResource(R.drawable.mid_bg);
                break;
            case BOTTOM:
                setBackgroundResource(R.drawable.bottom_bg);
                break;
        }
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resid) {
        int t = getPaddingTop();
        int l = getPaddingLeft();
        int r = getPaddingRight();
        int b = getPaddingBottom();

        super.setBackgroundResource(resid);

        setPadding(l, t, r, b);
    }

}
