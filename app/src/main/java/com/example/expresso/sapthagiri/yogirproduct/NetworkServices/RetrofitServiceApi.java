package com.example.expresso.sapthagiri.yogirproduct.NetworkServices;

import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.HttpRequests.CustomerGetter;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.HttpRequests.LoginGetter;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.HttpRequests.PasswordGetter;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.ServiceRequest.AddressGetter;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitServiceApi {

    @POST("http://192.168.0.4:8000/yrtp/consumer/register")
    Call<CustomerGetter> createPost(@Body JsonObject consumer_data);

    @PUT("http://192.168.0.4:8000/yrtp/verify-email")
    Call<ResponseBody> createPut(@Body JsonObject consumer_email);

    @POST("http://192.168.0.4:8000/yrtp/api-auth-token")
    Call<LoginGetter> loginUser(@Body JsonObject consumer_data);

    @PUT("http://192.168.0.4:8000/yrtp/change-password")
    Call<PasswordGetter> changePassword(@Header ("Authorization") String token, @Header("Content-Type") String type, @Body JsonObject consumer_data);

    @PUT("http://192.168.0.4:8000/yrtp/reset-password")
    Call<ResponseBody> resetPassword(@Header("Content-Type") String type, @Body JsonObject consumer_data);

    @PUT("http://192.168.0.4:8000/yrtp/forgot-password")
    Call<ResponseBody> verifyEmail(@Header("Content-Type") String type, @Body JsonObject consumer_data);

    @POST("http://192.168.0.4:8000/yrtp/consumer/address")
    Call<ResponseBody> createAddress(@Header ("Authorization") String token, @Header("Content-Type") String type, @Body JsonObject consumer_data);

    @GET("http://192.168.0.4:8000/yrtp/consumer/address")
    Call<List<AddressGetter>> getConsumerAddress(@Header ("Authorization") String token, @Header("Content-Type") String type);

    @PUT("http://192.168.0.4:8000/yrtp/consumer/address/{position}")
    Call<ResponseBody> updateAddress(@Header ("Authorization") String token, @Header("Content-Type") String type, @Body JsonObject consumer_data, @Path("position") int id);

    @HTTP(method = "DELETE", path = "http://192.168.0.4:8000/yrtp/consumer/address", hasBody = true)
    Call<ResponseBody> deleteAddress(@Header ("Authorization") String token, @Header("Content-Type") String type, @Body JsonObject consumer_data);
}

//@DELETE("http://192.168.0.4:8000/yrtp/consumer/address")
