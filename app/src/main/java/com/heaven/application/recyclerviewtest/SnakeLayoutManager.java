package com.heaven.application.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by caifangmao on 15/2/27.
 */
public class SnakeLayoutManager extends LinearLayoutManager {

    private int mDecoratedChildWidth;
    private int mDecoratedChildHeight;

    private Context context;
    private int screenWidth;
    private int screenHeight;

    public SnakeLayoutManager(Context context) {
        super(context);
        this.context = context;

        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state){
        super.onLayoutChildren(recycler, state);

        View scrap = recycler.getViewForPosition(0);
        addView(scrap);
        measureChildWithMargins(scrap, 0, 0);


        mDecoratedChildWidth = getDecoratedMeasuredWidth(scrap);
        mDecoratedChildHeight = getDecoratedMeasuredHeight(scrap);

        detachAndScrapView(scrap, recycler);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state){
        int delta = super.scrollVerticallyBy(dy, recycler, state);

        if(getChildCount() > 0){
            View topChild = getChildAt(0);
            int topOffset = getDecoratedTop(topChild);
            int count = getChildCount();

            int first = this.getPosition(topChild);

            for(int i = 0; i < count; i++){
                View child = getChildAt(i);
                int currentOffset = topOffset + i * mDecoratedChildHeight;

                double radian = ((float) currentOffset / (float) (screenHeight - mDecoratedChildHeight)) * Math.PI;

                int left = (int) (((i + first) % 2 == 0 ? Math.cos(radian) : Math.sin(radian)) * 350);

                if((i + first) % 2 == 0){
                    left = (int) (Math.sin(radian) * 350);
                }else{
                    left = (int) (Math.tan(radian) * 350);
                }

                layoutDecorated(child, left, currentOffset, mDecoratedChildWidth + left, mDecoratedChildHeight + currentOffset);
            }
        }

        return delta;
    }
}
