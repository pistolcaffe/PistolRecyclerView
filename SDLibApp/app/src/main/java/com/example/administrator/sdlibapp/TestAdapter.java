package com.example.administrator.sdlibapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015-12-29.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mDataList;

    public TestAdapter(Context context, ArrayList<String> dataList) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profile;
        private TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            profile = (ImageView) itemView.findViewById(R.id.test_profile);
            text = (TextView) itemView.findViewById(R.id.test_text);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.test_row, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
