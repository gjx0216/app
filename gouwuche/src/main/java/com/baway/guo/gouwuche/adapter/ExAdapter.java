package com.baway.guo.gouwuche.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.guo.gouwuche.R;
import com.baway.guo.gouwuche.me.Subview;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<GoodsEntity.DataBean> data;

    public ExAdapter(Context context, List<GoodsEntity.DataBean> data) {
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.groupitem, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.groupCk = convertView.findViewById(R.id.groupbox);
            groupViewHolder.groupName = convertView.findViewById(R.id.groupname);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.groupCk.setChecked(data.get(groupPosition).isIschecked());
        groupViewHolder.groupName.setText(data.get(groupPosition).getSellerName());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChileViewHolder chileViewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.childitem, null);
            chileViewHolder = new ChileViewHolder();
            chileViewHolder.childCk = convertView.findViewById(R.id.childbox);
            chileViewHolder.childImage = convertView.findViewById(R.id.childimage);
            chileViewHolder.childName = convertView.findViewById(R.id.childname);
            chileViewHolder.childPrice = convertView.findViewById(R.id.childprice);
            chileViewHolder.mSubview = convertView.findViewById(R.id.subview);
            convertView.setTag(chileViewHolder);
        } else {
            chileViewHolder = (ChileViewHolder) convertView.getTag();
        }
        chileViewHolder.childCk.setChecked(data.get(groupPosition).getList().get(childPosition).isCheck());
        chileViewHolder.childName.setText(data.get(groupPosition).getList().get(childPosition).getTitle());
        chileViewHolder.childPrice.setText("" + data.get(groupPosition).getList().get(childPosition).getPrice());
        String images = data.get(groupPosition).getList().get(childPosition).getImages();
        String[] split = images.split("!");
        if (split.length > 0) {
            Picasso.with(mContext).load(split[0]).into(chileViewHolder.childImage);
        }
        chileViewHolder.mSubview.setOnNunChangeListener(new Subview.onNunChangeListener() {
            @Override
            public void onNumChange(View view, int curNum) {
                data.get(groupPosition).getList().get(childPosition).setNum(curNum);
                if (mOnNunChangeListener != null) {
                    mOnNunChangeListener.onNumChange(view, curNum);
                }
            }
        });
        return convertView;
    }

    private Subview.onNunChangeListener mOnNunChangeListener;

    public void setOnNunChangeListener(Subview.onNunChangeListener onNunChangeListener) {
        this.mOnNunChangeListener = onNunChangeListener;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        CheckBox groupCk;
        TextView groupName;
    }

    class ChileViewHolder {
        CheckBox childCk;
        ImageView childImage;
        TextView childName;
        TextView childPrice;
        Subview mSubview;

    }
}
