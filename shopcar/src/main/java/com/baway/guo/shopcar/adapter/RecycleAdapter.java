package com.baway.guo.shopcar.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.guo.shopcar.MyAddSubView;
import com.baway.guo.shopcar.R;
import com.baway.guo.shopcar.been.GoodsEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.security.auth.callback.Callback;

public class RecycleAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<GoodsEntity.DataBean> data;

    public RecycleAdapter(Context context, List<GoodsEntity.DataBean> data) {
        mContext = context;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.shopcart_group, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.mCheckBox = convertView.findViewById(R.id.checkBox);
            groupViewHolder.shopname = convertView.findViewById(R.id.tvShopName);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.shopname.setText(data.get(groupPosition).getSellerName());
        groupViewHolder.mCheckBox.setChecked(data.get(groupPosition).isChecked());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHover childViewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.shopcart_child, null);
            childViewHolder = new ChildViewHover();
            childViewHolder.childbox = convertView.findViewById(R.id.childbox);
            childViewHolder.GsonName = convertView.findViewById(R.id.GSONname);
            childViewHolder.Gsonprice = convertView.findViewById(R.id.GSONprice);
            childViewHolder.GsonImg = convertView.findViewById(R.id.Gsonimg);
            childViewHolder.mAddSubView = convertView.findViewById(R.id.subView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHover) convertView.getTag();
        }
        //  childViewHolder.childbox.setChecked(data.get(groupPosition).getList().get(childPosition));
        childViewHolder.GsonName.setText(data.get(groupPosition).getList().get(childPosition).getTitle());
        childViewHolder.Gsonprice.setText(data.get(groupPosition).getList().get(childPosition).getPrice() + "");
        childViewHolder.mAddSubView.setCurentCount(data.get(groupPosition).getList().get(childPosition).getNum());
        final String images = data.get(groupPosition).getList().get(childPosition).getImages();
        String[] split = images.split("!");
        if (split.length > 0) {
            Picasso.with(mContext).load(split[0]).into(childViewHolder.GsonImg);
        }
        childViewHolder.mAddSubView.setOnNunChangerListener(new MyAddSubView.OnNunChangerListener() {
            @Override
            public void onNunChanger(View view, int curNum) {
                data.get(groupPosition).getList().get(childPosition).setNum(curNum);
                //未完///
                if (onNumChanged != null) {
                    onNumChanged.onNunChanger(view, curNum);
                }
            }
        });
        return convertView;
    }

    private MyAddSubView.OnNunChangerListener onNumChanged;

    public void setOnNumChanged(MyAddSubView.OnNunChangerListener onNumChanged) {
        this.onNumChanged = onNumChanged;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        CheckBox mCheckBox;
        TextView shopname;
    }

    class ChildViewHover {
        CheckBox childbox;
        ImageView GsonImg;
        TextView GsonName;
        TextView Gsonprice;
        MyAddSubView mAddSubView;
    }
}
