package com.baway.guo.gouwuche.me;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.guo.gouwuche.R;

public class Subview extends LinearLayout {

    private View mView;
    private TextView mJia;
    private TextView mNum;
    private TextView mJian;

    public Subview(Context context) {
        this(context, null);
    }

    public Subview(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public Subview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initListener();
    }

    private void initListener() {
        mJia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        mJian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jian();
            }
        });
    }

    private void jian() {
        String s = mNum.getText().toString();
        int i = Integer.parseInt(s);
        if (i > 1) {
            i--;
            setCurrCount(i);
        } else {
            Toast.makeText(getContext(), "不能再少了", Toast.LENGTH_LONG).show();
        }
    }

    private void setCurrCount(int i) {
        mNum.setText(""+i);
      if (mOnNunChangeListener!=null){
          mOnNunChangeListener.onNumChange(this,i);
      }
    }

    public interface onNunChangeListener {
        void onNumChange(View view, int curNum);
    }

    private onNunChangeListener mOnNunChangeListener;

    public void setOnNunChangeListener(onNunChangeListener onNunChangeListener) {
        this.mOnNunChangeListener = onNunChangeListener;
    }

    private void add() {
        String s = mNum.getText().toString();
        int i = Integer.parseInt(s);
        i++;
        setCurrCount(i);
    }

    private void initView(Context context) {
        mView = inflate(context, R.layout.subview, this);
        mJia = mView.findViewById(R.id.jia);
        mNum = mView.findViewById(R.id.num);
        mJian = mView.findViewById(R.id.jian);
    }
}
