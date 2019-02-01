package com.telstra.poc.ui;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.telstra.poc.R;
import com.telstra.poc.models.Row;
import com.telstra.poc.ui.FeedsViewHolder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

public class FeedsRecyclerViewAdapter extends RecyclerView.Adapter<FeedsViewHolder>{

    private FragmentManager supportFragmentManager;
    private List<Row> rowData;
    private Context mContext;

    public FeedsRecyclerViewAdapter(Context context, FragmentManager supportFragmentManager, List<Row> rowData) {
        this.rowData = rowData;
        this.mContext = context;
        this.supportFragmentManager = supportFragmentManager;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public FeedsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowsView = inflater.inflate(R.layout.feed_items, parent, false);
        FeedsViewHolder viewHolder = new FeedsViewHolder(rowsView, getContext(), supportFragmentManager);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FeedsViewHolder holder, int position) {
        Row mRowData = rowData.get(position);
        holder.bind(mRowData);
    }

    @Override
    public int getItemCount() {
        return rowData.size();
    }

    public void addAll(List<Row> list) {
        rowData.addAll(list);
        notifyDataSetChanged();
    }
}