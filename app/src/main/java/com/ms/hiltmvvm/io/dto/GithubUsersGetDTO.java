package com.ms.hiltmvvm.io.dto;

import com.ms.hiltmvvm.io.models.GithubUserModel;

import java.util.List;

public class GithubUsersGetDTO {
    private GithubUsersGetDTO users;

    public GithubUsersGetDTO getUsers() {
        return users;
    }

    public void setUsers(GithubUsersGetDTO users) {
        this.users = users;
    }
}
