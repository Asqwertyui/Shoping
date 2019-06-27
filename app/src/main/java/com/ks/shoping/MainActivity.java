package com.ks.shoping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ks.shoping.adpter.Myadapter;
import com.ks.shoping.bean.Food;
import com.ks.shoping.model.Mymodelimpl;
import com.ks.shoping.persent.Mypersentimpl;
import com.ks.shoping.view.Myview;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Myview {

    protected RecyclerView mRv;
    private ArrayList<Food.DataBean> mList;
    private EditText mEtprice;
    private Myadapter mMyadapter;
    private Mypersentimpl mMypersentimpl;
private int pp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mMypersentimpl = new Mypersentimpl(new Mymodelimpl(), this);
        mMypersentimpl.getData();
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mEtprice = (EditText) findViewById(R.id.etprice);
        mMyadapter = new Myadapter(mList, this);
        mRv.setAdapter(mMyadapter);
        mMyadapter.setOnClickListener(new Myadapter.OnClickListener() {
            @Override
            public void OnClick(int position, View view, Food.DataBean bean) {
                int num = bean.getNum();
                Boolean check = bean.getCheck();
                gnum(num,check);
            }
        });
    }

    private void gnum(int num, Boolean check) {
        if(check){
            pp+=num;
        }else {
            pp-=num;
        }
        mEtprice.setText(pp+"");
    }

    @Override
    public void OnSuccess(Food food) {
        if (food != null) {
            List<Food.DataBean> data = food.getData();
            mList.addAll(data);
            mMyadapter.notifyDataSetChanged();
        }
    }

    @Override
    public void OnFail(String msg) {
        Log.e("tag", msg);
    }
}
