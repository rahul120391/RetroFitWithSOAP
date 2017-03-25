package com.example.retrofitwithsoap;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface MyRetrofit
{
	@Headers({
        "Content-Type: application/soap+xml",
        "Accept-Charset: utf-8"
    })
    @POST("/api.asmx")
	void getdata(@Body RequestEnvelope request,Callback<String> callback);
}
