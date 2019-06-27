package com.ks.shoping.view;

import com.ks.shoping.bean.Food;

/**
 * Created by F0519 on 2019/6/27.
 */

public interface Myview {
    void OnSuccess(Food food);
    void OnFail(String msg);
}
