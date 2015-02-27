package com.heaven.application.recyclerviewtest;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by caifangmao on 15/2/27.
 */
public class GridLayoutManager extends StaggeredGridLayoutManager {

    public GridLayoutManager(int span, int orientation){
        super(span, orientation);
    }

    @Override
    public boolean canScrollVertically(){
        return true;
    }

    @Override
    public boolean canScrollHorizontally(){
        return true;
    }
}
