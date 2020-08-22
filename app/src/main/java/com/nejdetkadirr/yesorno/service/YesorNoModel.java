package com.nejdetkadirr.yesorno.service;

import com.google.gson.annotations.SerializedName;

public class YesorNoModel {

    @SerializedName("answer")
    public String answer;

    @SerializedName("forced")
    public String forced;

    @SerializedName("image")
    public String image;

}
