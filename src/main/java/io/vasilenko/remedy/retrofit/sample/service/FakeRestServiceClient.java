package io.vasilenko.remedy.retrofit.sample.service;

import io.vasilenko.remedy.retrofit.sample.dto.User;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface FakeRestServiceClient {

    @GET("/vasilenkosergey/fake-online-rest-server/users")
    Call<List<User>> getUsers();
}
