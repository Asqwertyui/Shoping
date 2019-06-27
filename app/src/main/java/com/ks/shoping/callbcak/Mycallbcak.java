package com.ks.shoping.callbcak;

import com.ks.shoping.bean.Food;

/**
 * Created by F0519 on 2019/6/27.
 */

public interface Mycallbcak {
    void OnSuccess(Food food);
    void OnFail(String msg);
}
