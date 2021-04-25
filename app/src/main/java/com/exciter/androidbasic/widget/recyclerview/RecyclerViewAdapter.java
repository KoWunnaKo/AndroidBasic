package com.exciter.androidbasic.widget.recyclerview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exciter.androidbasic.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

    private Context mContext;
    private List<ItemBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewAdapter(Context mContext, List<ItemBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        if (null != mData) {
            holder.mTvTitle.setText(TextUtils.isEmpty(mData.get(position).getTitle()) ? "no title" : mData.get(position).getTitle());
            ViewGroup.LayoutParams params = holder.mTvTitle.getLayoutParams();
            params.height = mData.get(position).getHeight();
            holder.mTvTitle.setLayoutParams(params);
        }
        if (null != mOnItemClickListener) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(holder.itemView, position));
            holder.itemView.setOnLongClickListener(v -> {
                mOnItemClickListener.onItemLongClick(holder.itemView, position);
                return false;
            });
            holder.mTvTitle.setOnClickListener(v -> mOnItemClickListener.onItemChildClick(holder.mTvTitle, position));
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private final TextView mTvTitle;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        /**
         * 点击item
         *
         * @param view     item
         * @param position 索引
         */
        void onItemClick(View view, int position);

        /**
         * 长按item
         *
         * @param view     item
         * @param position 索引
         */
        void onItemLongClick(View view, int position);

        /**
         * 点击item中的子view
         *
         * @param view     子view
         * @param position 索引
         */
        void onItemChildClick(View view, int position);
    }
}
