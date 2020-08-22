package com.nejdetkadirr.yesorno.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface YesorNoAPI {
    @GET("yesorno.php")
    Observable<List<YesorNoModel>> getData();
}
