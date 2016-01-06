package com.example.administrator.sdlibapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.pistolcaffe.lib.pistolrecyclerview.PistolRecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TestAdapter mAdapter;
    private PistolRecyclerView mRecyclerView;

    private Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        mRecyclerView = (PistolRecyclerView) findViewById(R.id.pistolrecyclewview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setOnPistolNavigationButtonClickListener(mListener);

        ArrayList<String> data = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            data.add("TEST :: ORG :: " + mRandom.nextInt(100));
        }

        mAdapter = new TestAdapter(this, data);
        mRecyclerView.setListInfo(10, 80);
        mRecyclerView.setAdapter(mAdapter);
    }

    private final PistolRecyclerView.OnPistolNavigationButtonClickListener mListener = new PistolRecyclerView.OnPistolNavigationButtonClickListener() {
        @Override
        public void onClick(int direction) {
            if (direction == PistolRecyclerView.LEFT) {
                ArrayList<String> data = new ArrayList<String>();
                for (int i = 0; i < 10; i++) {
                    data.add("TEST :: LEFT :: " + mRandom.nextInt(100));
                }
                mAdapter = new TestAdapter(MainActivity.this, data);
            } else if (direction == PistolRecyclerView.RIGHT) {
                ArrayList<String> data = new ArrayList<String>();
                for (int i = 0; i < 10; i++) {
                    data.add("TEST :: RIGHT :: " + mRandom.nextInt(100));
                }
                mAdapter = new TestAdapter(MainActivity.this, data);
            }
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.onLoadComplete();
        }
    };
}