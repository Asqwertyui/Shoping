package com.ks.shoping.bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by F0519 on 2019/6/27.
 */

public interface Myservice {
    //http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1
    public String url = "http://www.qubaobei.com/ios/cf/";

    @GET("dish_list.php?stage_id=1&limit=20&page=1")
    Observable<Food> getfood();
}
