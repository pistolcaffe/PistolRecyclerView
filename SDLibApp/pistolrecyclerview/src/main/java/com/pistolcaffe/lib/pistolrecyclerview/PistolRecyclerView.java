package com.pistolcaffe.lib.pistolrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by pistolcaffe on 2015-12-28.
 */
public class PistolRecyclerView extends RelativeLayout {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    private RecyclerView mRecyclerView;
    private BottomListNavigationView mBottomNavigationView;
    private OnPistolNavigationButtonClickListener mNavigationButtonClickListener;

    /* Attribute */
    private int mLeftBtnResId;
    private int mRightBtnResId;

    public interface OnPistolNavigationButtonClickListener {
        public void onClick(int direction);
    }

    public PistolRecyclerView(Context context) {
        this(context, null, 0);
    }

    public PistolRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PistolRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            buildAttrs(attrs);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pistol_recycler_view_layout, this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mBottomNavigationView = (BottomListNavigationView) view.findViewById(R.id.bottom_navigation_view);
        mBottomNavigationView.setOnNavigationButtonClickListener(mListener);

        if (mLeftBtnResId != 0) {
            mBottomNavigationView.setLeftButtonImageResource(mLeftBtnResId);
        }

        if (mRightBtnResId != 0) {
            mBottomNavigationView.setRightButtonImageResource(mRightBtnResId);
        }
    }

    private void buildAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PistolRecyclerView);
        try {
            mLeftBtnResId = typedArray.getResourceId(R.styleable.PistolRecyclerView_leftButtonSrc, 0);
            mRightBtnResId = typedArray.getResourceId(R.styleable.PistolRecyclerView_rightButtonSrc, 0);
        } finally {
            typedArray.recycle();
        }
    }

    public void setOnPistolNavigationButtonClickListener(OnPistolNavigationButtonClickListener listener) {
        mNavigationButtonClickListener = listener;
    }

    public void setLeftNavigationButtonResource(int resId) {
        mBottomNavigationView.setLeftButtonImageResource(resId);
    }

    public void setRightNavigationButtonResource(int resId) {
        mBottomNavigationView.setRightButtonImageResource(resId);
    }

    public void setLeftNavigationButtonVisibility(int state) {
        mBottomNavigationView.setLeftButtonVisibility(state);
    }

    public void setRightNavigationButtonVisibility(int state) {
        mBottomNavigationView.setRightButtonVisibility(state);
    }

    public void isLoading() {
        mBottomNavigationView.isLoading();
    }

    public void onLoadComplete() {
        mBottomNavigationView.onLoadComplete();
    }

    public void setListInfo(int wantVisibleItemPerPage, int itemTotalCount) {
        mBottomNavigationView.buildParams(wantVisibleItemPerPage, itemTotalCount);
    }

    /**
     * Set the layout manager to the recycler
     *
     * @param manager lm
     */
    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        mRecyclerView.setLayoutManager(manager);
    }

    /**
     * Get the adapter of UltimateRecyclerview
     *
     * @return ad
     */
    public RecyclerView.Adapter getAdapter() {
        return mRecyclerView.getAdapter();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (!mBottomNavigationView.isSetBuildParams()) {
            throw new NotRegisteredListInfoException();
        }
        mRecyclerView.setAdapter(adapter);
    }

    public void setHasFixedSize(boolean hasFixedSize) {
        mRecyclerView.setHasFixedSize(hasFixedSize);
    }

    public void removeItemDecoration(RecyclerView.ItemDecoration decoration) {
        mRecyclerView.removeItemDecoration(decoration);
    }

    public void addOnItemTouchListener(RecyclerView.OnItemTouchListener listener) {
        mRecyclerView.addOnItemTouchListener(listener);
    }

    public void removeOnItemTouchListener(RecyclerView.OnItemTouchListener listener) {
        mRecyclerView.removeOnItemTouchListener(listener);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mRecyclerView.getLayoutManager();
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener customOnScrollListener) {
        mRecyclerView.setOnScrollListener(customOnScrollListener);
    }

    public void addOnScrollListener(RecyclerView.OnScrollListener customOnScrollListener) {
        mRecyclerView.addOnScrollListener(customOnScrollListener);
    }

    public void removeOnScrollListener(RecyclerView.OnScrollListener customOnScrollListener) {
        mRecyclerView.removeOnScrollListener(customOnScrollListener);
    }

    /**
     * Swaps the current adapter with the provided one. It is similar to
     * {@link #setAdapter(RecyclerView.Adapter)} but assumes existing adapter and the new adapter uses the same
     * ViewHolder and does not clear the RecycledViewPool.
     * <p/>
     * Note that it still calls onAdapterChanged callbacks.
     *
     * @param adapter                       The new adapter to set, or null to set no adapter.
     * @param removeAndRecycleExistingViews If set to true, RecyclerView will recycle all existing Views. If adapters have stable ids and/or you want to animate the disappearing views, you may prefer to set this to false.
     */

    public void swapAdapter(RecyclerView.Adapter adapter, boolean removeAndRecycleExistingViews) {
        mRecyclerView.swapAdapter(adapter, removeAndRecycleExistingViews);
    }

    /**
     * Add an {@link RecyclerView.ItemDecoration} to this RecyclerView. Item decorations can affect both measurement and drawing of individual item views. Item decorations are ordered. Decorations placed earlier in the list will be run/queried/drawn first for their effects on item views. Padding added to views will be nested; a padding added by an earlier decoration will mean further item decorations in the list will be asked to draw/pad within the previous decoration's given area.
     *
     * @param itemDecoration Decoration to add
     */
    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    /**
     * Add an {@link RecyclerView.ItemDecoration} to this RecyclerView. Item decorations can affect both measurement and drawing of individual item views.
     * <p>Item decorations are ordered. Decorations placed earlier in the list will be run/queried/drawn first for their effects on item views. Padding added to views will be nested; a padding added by an earlier decoration will mean further item decorations in the list will be asked to draw/pad within the previous decoration's given area.</p>
     *
     * @param itemDecoration Decoration to add
     * @param index          Position in the decoration chain to insert this decoration at. If this value is negative the decoration will be added at the end.
     */
    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration, int index) {
        mRecyclerView.addItemDecoration(itemDecoration, index);
    }

    /**
     * Sets the {@link RecyclerView.ItemAnimator} that will handle animations involving changes
     * to the items in this RecyclerView. By default, RecyclerView instantiates and
     * uses an instance of {@link android.support.v7.widget.DefaultItemAnimator}. Whether item animations are enabled for the RecyclerView depends on the ItemAnimator and whether
     * the LayoutManager {@link RecyclerView.LayoutManager#supportsPredictiveItemAnimations()
     * supports item animations}.
     *
     * @param animator The ItemAnimator being set. If null, no animations will occur
     *                 when changes occur to the items in this RecyclerView.
     */
    public void setItemAnimator(RecyclerView.ItemAnimator animator) {
        mRecyclerView.setItemAnimator(animator);
    }

    /**
     * Gets the current ItemAnimator for this RecyclerView. A null return value
     * indicates that there is no animator and that item changes will happen without
     * any animations. By default, RecyclerView instantiates and
     * uses an instance of {@link android.support.v7.widget.DefaultItemAnimator}.
     *
     * @return ItemAnimator The current ItemAnimator. If null, no animations will occur
     * when changes occur to the items in this RecyclerView.
     */
    public RecyclerView.ItemAnimator getItemAnimator() {
        return mRecyclerView.getItemAnimator();
    }

    private final BottomListNavigationView.OnNavigationButtonClickListener mListener = new BottomListNavigationView.OnNavigationButtonClickListener() {
        @Override
        public void onClick(final int direction) {
            if (mNavigationButtonClickListener != null) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mNavigationButtonClickListener.onClick(direction == BottomListNavigationView.NAVIGATION_LEFT ? LEFT : RIGHT);
                    }
                }, 800);
            }
        }
    };
}