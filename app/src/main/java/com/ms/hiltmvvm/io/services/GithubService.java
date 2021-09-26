package com.ms.hiltmvvm.io.services;


import com.ms.hiltmvvm.io.dto.GithubUsersGetDTO;
import com.ms.hiltmvvm.io.models.GithubUserModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubService {
    @GET("users")
    Call<List<GithubUserModel>> getUsers();

}
