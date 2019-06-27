package com.ks.shoping.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ks.shoping.R;
import com.ks.shoping.bean.Food;

import java.util.ArrayList;

/**
 * Created by F0519 on 2019/6/27.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHodler> {
    private ArrayList<Food.DataBean> mList;

    public ArrayList<Food.DataBean> getList() {
        return mList;
    }

    public void setList(ArrayList<Food.DataBean> list) {
        mList = list;
    }
    private Context context;
    public Myadapter(ArrayList<Food.DataBean> list, Context context) {
        mList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        ViewHodler hodler = new ViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, final int position) {
        final Food.DataBean bean = mList.get(position);
        holder.tv.setText(String.valueOf(bean.getNum()));
        Glide.with(context).load(bean.getPic()).into(holder.iv);
        holder.cb.setChecked(bean.getCheck());
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              bean.setCheck(!bean.getCheck());
              if(OnClickListener!=null){
                  OnClickListener.OnClick(position,buttonView,bean);
              }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView iv;
        private CheckBox cb;

        public ViewHodler(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
            cb = itemView.findViewById(R.id.cb);
        }
    }

    private OnClickListener OnClickListener;

    public void setOnClickListener(Myadapter.OnClickListener onClickListener) {
        OnClickListener = onClickListener;
    }

    public interface OnClickListener {
        void OnClick(int position, View view, Food.DataBean bean);
    }
}
