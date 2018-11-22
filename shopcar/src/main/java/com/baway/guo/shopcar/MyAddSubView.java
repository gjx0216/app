package com.baway.guo.shopcar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyAddSubView extends LinearLayout {

    private View mView;
    private TextView mAdd;
    private TextView mNum;
    private TextView mRemove;

    public MyAddSubView(Context context) {
        this(context, null);
    }

    public MyAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);

    }

    public MyAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        intiListener();

    }


    private void initView(Context context) {
        mView = View.inflate(context, R.layout.add_remove_view, this);
        mAdd = mView.findViewById(R.id.add_tv);
        mNum = mView.findViewById(R.id.num);
        mRemove = mView.findViewById(R.id.remove_tv);
        mNum.setText("1");
    }

    private void intiListener() {

        mAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }

            private void add() {
                String cs = mNum.getText().toString();
                int parseInt = Integer.parseInt(cs);
                parseInt++;
                setCurentCount(parseInt);


            }

        });
        mRemove.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
            }
            private void remove() {
                String cs = mNum.getText().toString();
                int parseInt = Integer.parseInt(cs);
                if (parseInt > 1) {
                    parseInt--;
                    setCurentCount(parseInt);

                } else {
                    Toast.makeText(getContext(), "不能再少了亲", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public int getCurentCount() {
        return Integer.parseInt(mNum.getText().toString());
    }

    public void setCurentCount(int number) {
        mNum.setText(number + "");
        if (OnNunChangerListener != null) {
            OnNunChangerListener.onNunChanger(this, number);
        }
    }

    private OnNunChangerListener OnNunChangerListener;

    public interface OnNunChangerListener {
        void onNunChanger(View view, int curNum);
    }

    public void setOnNunChangerListener(MyAddSubView.OnNunChangerListener onNunChangerListener) {
        this.OnNunChangerListener = onNunChangerListener;
    }
}
