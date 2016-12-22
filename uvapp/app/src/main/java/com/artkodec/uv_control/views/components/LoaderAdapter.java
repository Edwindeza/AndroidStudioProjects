package com.artkodec.uv_control.views.components;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.artkodec.uv_control.R;

import java.util.List;

public abstract class LoaderAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected boolean showLoader;
    private static final int VIEWTYPE_ITEM = 1;
    private static final int VIEWTYPE_LOADER = 2;

    protected List<T> mItems;
    protected LayoutInflater mInflater;

    public List<T> getmItems() {
        return mItems;
    }

    public void setmItems(List<T> mItems) {
        this.mItems = mItems;
    }

    public LoaderAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == VIEWTYPE_LOADER) {

            // Your Loader XML view here
            View view = mInflater.inflate(R.layout.item_loader, viewGroup, false);

            // Your LoaderViewHolder class
            return new LoaderViewHolder(view);

        } else if (viewType == VIEWTYPE_ITEM) {
            return getYourItemViewHolder(viewGroup);
        }

        throw new IllegalArgumentException("Invalid ViewType: " + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        // Loader ViewHolder
        if (viewHolder instanceof LoaderViewHolder) {
            LoaderViewHolder loaderViewHolder = (LoaderViewHolder)viewHolder;
            if (showLoader) {
                loaderViewHolder.mProgressBar.setVisibility(View.VISIBLE);
            } else {
                loaderViewHolder.mProgressBar.setVisibility(View.GONE);
            }

            return;
        }

        bindYourViewHolder(viewHolder, position);

    }

    @Override
    public int getItemCount() {

        // If no items are present, there's no need for loader
        if (mItems == null || mItems.size() == 0) {
            return 0;
        }

        // +1 for loader
        return mItems.size() + 1;
    }

    @Override
    public long getItemId(int position) {

        // loader can't be at position 0
        // loader can only be at the last position
        if (position != 0 && position == getItemCount() - 1) {

            // id of loader is considered as -1 here
            return -1;
        }
        return getYourItemId(position);
    }

    public void addItem(T item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void addItemFirst(T item) {
        mItems.add(0,item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        // loader can't be at position 0
        // loader can only be at the last position
        if (position != 0 && position == getItemCount() - 1) {
            return VIEWTYPE_LOADER;
        }

        return VIEWTYPE_ITEM;
    }


    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }


    public void showLoading(boolean status) {
        showLoader = status;
    }

    public void setItems(List<T> items) {
        mItems = items;
    }

    public abstract long getYourItemId(int position);
    public abstract RecyclerView.ViewHolder getYourItemViewHolder(ViewGroup parent);
    public abstract void bindYourViewHolder(RecyclerView.ViewHolder holder, int position);

}