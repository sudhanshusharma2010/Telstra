package com.telstra.poc.ui;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.telstra.poc.R;
import com.telstra.poc.module.GlideApp;
import com.telstra.poc.models.Row;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class FeedsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.feed_relative)
    RelativeLayout mRelativeLayout;

    @BindView(R.id.feed_title)
    AppCompatTextView mTitle;

    @BindView(R.id.feed_desc)
    AppCompatTextView mDesc;

    @BindView(R.id.feed_item_image)
    ImageView mImage;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private final Context context;
    private Row rowData;

    public FeedsViewHolder(final View itemView, Context context, FragmentManager supportFragmentManager) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
    }

    public void bind(Row rowData) {
        this.rowData = rowData;
        if(rowData.getTitle() != null && !rowData.getTitle().equalsIgnoreCase("")) {
            mRelativeLayout.setVisibility(View.VISIBLE);
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText(rowData.getTitle());
        }
        else {
            mRelativeLayout.setVisibility(View.GONE);
            mTitle.setVisibility(View.GONE);
        }
        if(rowData.getDescription() != null && !rowData.getDescription().equalsIgnoreCase("")) {
            mRelativeLayout.setVisibility(View.VISIBLE);
            mDesc.setVisibility(View.VISIBLE);
            mDesc.setText(rowData.getDescription());
        }
        else {
            mRelativeLayout.setVisibility(View.GONE);
            mDesc.setVisibility(View.GONE);
        }

        GlideApp.with(context)
                .load(rowData.getImageHref())
                .transform(new RoundedCornersTransformation(10, 10))
                .override(300,300)
                .into(mImage);
    }
}