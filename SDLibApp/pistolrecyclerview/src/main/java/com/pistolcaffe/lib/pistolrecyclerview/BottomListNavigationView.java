package com.pistolcaffe.lib.pistolrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

class BottomListNavigationView extends RelativeLayout {

    public static final int NAVIGATION_LEFT = 0;
    public static final int NAVIGATION_RIGHT = 1;

    private FrameLayout mNavigationLeftBtnFrame;
    private FrameLayout mNavigationRightBtnFrame;
    private ImageView mNavigationLeftBtn;
    private ImageView mNavigationRightBtn;
    private TextView mPageIndexCountView;
    private SmoothProgressBar mSmoothProgressBar;
    private View mTopLine;
    private OnNavigationButtonClickListener mListener;
    private int mCurrentPage = 1;
    private int mMaxPage = 0;
    private boolean mIsLoading = false;
    private boolean mIsSetParams = false;

    public interface OnNavigationButtonClickListener {
        public void onClick(int direction);
    }

    public BottomListNavigationView(Context context) {
        this(context, null, 0);
    }

    public BottomListNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomListNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mNavigationLeftBtnFrame = (FrameLayout) findViewById(R.id.left_btn_frame);
        mNavigationRightBtnFrame = (FrameLayout) findViewById(R.id.right_btn_frame);
        mNavigationLeftBtn = (ImageView) findViewById(R.id.left_btn);
        mNavigationRightBtn = (ImageView) findViewById(R.id.right_btn);
        mPageIndexCountView = (TextView) findViewById(R.id.page_index);
        mSmoothProgressBar = (SmoothProgressBar) findViewById(R.id.smooth_progress_bar);
        mTopLine = (View) findViewById(R.id.top_line);

        mNavigationLeftBtnFrame.setOnClickListener(mClickListener);
        mNavigationRightBtnFrame.setOnClickListener(mClickListener);
    }

    public void setOnNavigationButtonClickListener(OnNavigationButtonClickListener listener) {
        mListener = listener;
    }

    public void onLoadComplete() {
        mTopLine.setVisibility(View.VISIBLE);
        mSmoothProgressBar.setVisibility(View.GONE);
        mIsLoading = false;

        mPageIndexCountView.setText(String.valueOf(mCurrentPage) + " / " + mMaxPage);
        mNavigationLeftBtnFrame.setVisibility(mCurrentPage == 1 ? View.GONE : View.VISIBLE);
        mNavigationRightBtnFrame.setVisibility(mCurrentPage == mMaxPage ? View.GONE : View.VISIBLE);
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    public boolean isSetBuildParams() {
        return mIsSetParams;
    }

    public void buildParams(int wantVisibleItemPerPage, int totalCount) {
        mIsSetParams = true;
        mMaxPage = (totalCount / wantVisibleItemPerPage) + (totalCount % wantVisibleItemPerPage == 0 ? 0 : 1);
        mNavigationLeftBtnFrame.setVisibility(mCurrentPage == 1 ? View.GONE : View.VISIBLE);
        mPageIndexCountView.setText("1 / " + mMaxPage);
    }

    public void setLeftButtonImageResource(int resId) {
        mNavigationLeftBtn.setImageResource(resId);
    }

    public void setRightButtonImageResource(int resId) {
        mNavigationRightBtn.setImageResource(resId);
    }

    public void setLeftButtonVisibility(int state) {
        mNavigationLeftBtn.setVisibility(state);
    }

    public void setRightButtonVisibility(int state) {
        mNavigationRightBtn.setVisibility(state);
    }

    private final OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null && !mIsLoading) {
                mIsLoading = true;
                mSmoothProgressBar.setVisibility(View.VISIBLE);
                mTopLine.setVisibility(View.GONE);

                mListener.onClick(v.equals(mNavigationLeftBtnFrame) ? NAVIGATION_LEFT : NAVIGATION_RIGHT);
                mCurrentPage = v.equals(mNavigationLeftBtnFrame) ? mCurrentPage - 1 : mCurrentPage + 1;
            }
        }
    };
}