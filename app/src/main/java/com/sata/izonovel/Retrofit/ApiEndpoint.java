package com.sata.izonovel.Retrofit;

import com.sata.izonovel.Model.InsertNovelMOdel;
import com.sata.izonovel.Model.InsertResponseModel;
import com.sata.izonovel.Model.LoginRequestModel;
import com.sata.izonovel.Model.LoginResponseModel;
import com.sata.izonovel.Model.RegisterRequestModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiEndpoint
{
    String API_KEY = "8ZQDmrtgC0RX5AVLVQV5YjyS1pA1D7Sa7HZtlTSViEA58X8CUl8mueSLqHd3Md3y";

    @Headers({"api-key:" + API_KEY, "Content-Type:application/json"})
    @POST("app/data-yvczw/endpoint/data/v1/action/insertOne")
    Call<InsertResponseModel> registerUser(@Body RegisterRequestModel registerRequestModel);

    @Headers({"api-key:" + API_KEY, "Content-Type:application/json"})
    @POST("app/data-yvczw/endpoint/data/v1/action/findOne")
    Call<LoginResponseModel> onSubmitLogin(@Body LoginRequestModel loginRequestModel);

    @Headers({"api-key:" + API_KEY, "Content-Type:application/json"})
    @POST("app/data-yvczw/endpoint/data/v1/action/insertOne")
    Call<InsertNovelMOdel> insertNovel(@Body InsertNovelMOdel insertNovelMOdel);









}
