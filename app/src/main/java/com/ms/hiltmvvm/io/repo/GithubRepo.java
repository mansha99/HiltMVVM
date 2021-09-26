package com.ms.hiltmvvm.io.repo;

import com.ms.hiltmvvm.io.dto.GithubUsersGetDTO;
import com.ms.hiltmvvm.io.models.GithubUserModel;
import com.ms.hiltmvvm.io.services.GithubService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;

public class GithubRepo {
    private GithubService githubService;

    @Inject
    public GithubRepo(GithubService githubService) {
        this.githubService = githubService;
    }

    public Call<List<GithubUserModel>> getUsers() {
        return githubService.getUsers();
    }

}
