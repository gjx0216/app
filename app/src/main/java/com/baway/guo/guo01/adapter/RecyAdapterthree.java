package com.baway.guo.guo01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.guo.guo01.R;
import com.baway.guo.guo01.entity.User1;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyAdapterthree extends RecyclerView.Adapter {

    private Context mContext;
    private List<User1.DataBean.ListBean> mList;

    public RecyAdapterthree(Context context, List<User1.DataBean.ListBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.itemthree, viewGroup, false);
        MyViewholder myViewholder = new MyViewholder(view);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewholder) {
            Picasso.with(mContext).load(mList.get(i).getIcon()).into(((MyViewholder) viewHolder).mImageView);
            ((MyViewholder) viewHolder).mName3.setText(mList.get(i).getName());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        private final TextView mName3;
        private final ImageView mImageView;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.img);
            mName3 = itemView.findViewById(R.id.name3);

        }
    }

}
