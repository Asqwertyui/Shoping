package com.ks.shoping.persent;

import com.ks.shoping.bean.Food;
import com.ks.shoping.callbcak.Mycallbcak;
import com.ks.shoping.model.Mymodel;
import com.ks.shoping.view.Myview;

/**
 * Created by F0519 on 2019/6/27.
 */

public class Mypersentimpl implements Mypersent, Mycallbcak {
    private Mymodel mymodel;
    private Myview myview;

    public Mypersentimpl(Mymodel mymodel, Myview myview) {
        this.mymodel = mymodel;
        this.myview = myview;
    }

    @Override
    public void getData() {
        if(mymodel!=null){
            mymodel.getData(this);
        }
    }

    @Override
    public void OnSuccess(Food food) {
        if(myview!=null){
            myview.OnSuccess(food);
        }
    }
    @Override
    public void OnFail(String msg) {
        if(myview!=null){
            myview.OnFail(msg);
        }
    }
}
