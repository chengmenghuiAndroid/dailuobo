package com.daluobo.com.mvp.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * Created by wanglei on 2016/11/29.
 */

public abstract class SimpleRecAdapter<T, F extends RecyclerView.ViewHolder> extends RecyclerAdapter<T, F> {

    public SimpleRecAdapter(Context context) {
        super(context);
    }

    @Override
    public F onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        return newViewHolder(view);
    }

    public abstract F newViewHolder(View itemView);

    public abstract int getLayoutId();

}
