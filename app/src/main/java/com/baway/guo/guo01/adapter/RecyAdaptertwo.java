package com.baway.guo.guo01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.guo.guo01.R;
import com.baway.guo.guo01.entity.User1;

import java.util.List;

public class RecyAdaptertwo extends RecyclerView.Adapter {

    private Context mContext;
    private List<User1.DataBean> mList;

    public RecyAdaptertwo(Context context, List<User1.DataBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.itemtwo, viewGroup, false);
        MyViewholder myViewholder = new MyViewholder(view);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewholder) {
            ((MyViewholder) viewHolder).mName1.setText(mList.get(i).getPcid());
            ((MyViewholder) viewHolder).mRecyclerView3.setLayoutManager(new GridLayoutManager(mContext,1));
            ((MyViewholder) viewHolder).mRecyclerView3.setAdapter(new RecyAdapterthree(mContext,mList.get(i).getList()));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        private final TextView mName1;
        private final RecyclerView mRecyclerView3;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            mName1 = itemView.findViewById(R.id.name1);
            mRecyclerView3 = itemView.findViewById(R.id.recycle3);
        }
    }

}
