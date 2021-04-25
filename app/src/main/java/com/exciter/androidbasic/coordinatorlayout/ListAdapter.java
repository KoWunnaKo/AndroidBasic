package com.exciter.androidbasic.coordinatorlayout;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exciter.androidbasic.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyHolder> {

    private List<ListBean> mData;

    public ListAdapter(List<ListBean> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        if (mData == null || mData.size() < position + 1 || mData.get(position) == null) {
            return;
        }
        holder.mIvCover.setImageResource(mData.get(position).getCover());
        holder.mTvTitle.setText(TextUtils.isEmpty(mData.get(position).getTitle()) ? "" : mData.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView mIvCover;
        private final TextView mTvTitle;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mIvCover = itemView.findViewById(R.id.iv_cover);
            mTvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
