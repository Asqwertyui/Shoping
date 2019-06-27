package com.ks.shoping.model;

import android.util.Log;

import com.ks.shoping.bean.Food;
import com.ks.shoping.bean.Myservice;
import com.ks.shoping.callbcak.Mycallbcak;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by F0519 on 2019/6/27.
 */

public class Mymodelimpl implements Mymodel {
    @Override
    public void getData(final Mycallbcak mycallbcak) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Myservice.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Myservice myservice = retrofit.create(Myservice.class);
        Observable<Food> getfood = myservice.getfood();
        getfood.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Food>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Food food) {
                        if (food != null) {
                            mycallbcak.OnSuccess(food);
                        } else {
                            mycallbcak.OnFail("no");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("tag", "onComplete");
                    }
                });
    }
}
