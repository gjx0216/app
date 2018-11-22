package com.baway.guo.guo01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.guo.guo01.R;
import com.baway.guo.guo01.entity.User;

import java.util.List;

public class RecyAdapterone extends RecyclerView.Adapter {

    private Context mContext;
    private List<User.DataBean> mData;

    public RecyAdapterone(Context context, List<User.DataBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.itemone, viewGroup, false);
        MyViewHolder holder1 = new MyViewHolder(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            ((MyViewHolder) viewHolder).mName.setText(mData.get(i).getName());
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public interface OnItemCliklitener {
        void OnClik(int i);
    }

    private OnItemCliklitener mOnItemCliklitener;

    public void setOnItemCliklitener(OnItemCliklitener onItemCliklitener) {
        this.mOnItemCliklitener = onItemCliklitener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView mName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemCliklitener.OnClik( getAdapterPosition());
                }
            });
        }
    }
}
