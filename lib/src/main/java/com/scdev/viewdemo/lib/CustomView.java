package com.scdev.viewdemo.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
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

    public enum ViewState {
        NORMAL,
        WARNING,
        ERROR
    }

    private static final int[] STATE_NORMAL = {R.attr.state_normal};
    private static final int[] STATE_WARNING = {R.attr.state_warning};
    private static final int[] STATE_ERROR = {R.attr.state_error};

    private ViewState mViewState = null;

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

        setClickable(!isLoading);

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

    public void setViewState(ViewState state) {
        if (state != mViewState) {
            mViewState = state;
            refreshDrawableState();
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        if (mViewState == null) {
            return super.onCreateDrawableState(extraSpace);
        }

        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);

        switch (mViewState) {
            case NORMAL:
                mergeDrawableStates(drawableState, STATE_NORMAL);
                break;
            case WARNING:
                mergeDrawableStates(drawableState, STATE_WARNING);
                break;
            case ERROR:
                mergeDrawableStates(drawableState, STATE_ERROR);
                break;
        }

        return drawableState;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);

        ss.secondary = mSecondaryLabel.getText();
        ss.position = mPosition;
        ss.viewState = mViewState == null ? ViewState.NORMAL.ordinal() : mViewState.ordinal();
        ss.isLoading = mIsLoading;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        mSecondaryLabel.setText(ss.secondary);
        setPosition(ss.position);
        setViewState(ViewState.values()[ss.viewState]);
        setIsLoading(ss.isLoading);
    }

    static class SavedState extends BaseSavedState {
        CharSequence secondary;
        int position;
        int viewState;
        boolean isLoading;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);

            secondary = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
            position = in.readInt();
            viewState = in.readInt();
            isLoading = in.readByte() == 0 ? false : true;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);

            TextUtils.writeToParcel(secondary, out, 0);
            out.writeInt(position);
            out.writeInt(viewState);
            out.writeByte((byte) (isLoading ? 1 : 0));
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

}
