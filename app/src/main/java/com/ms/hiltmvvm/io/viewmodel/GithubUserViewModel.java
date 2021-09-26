package com.ms.hiltmvvm.io.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ms.hiltmvvm.io.models.GithubUserModel;
import com.ms.hiltmvvm.io.repo.GithubRepo;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class GithubUserViewModel extends ViewModel {
    private static final String TAG = "GithubUserModelViewModel";

    private GithubRepo githubRepository;
    private MutableLiveData<List<GithubUserModel>> users = new MutableLiveData<>();
    private MutableLiveData<Boolean> isError = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();


    @Inject
    public GithubUserViewModel(GithubRepo githubRepository) {
        this.githubRepository = githubRepository;
    }

    private static void accept(Object result) {

    }

    public MutableLiveData<List<GithubUserModel>> getGithubUserModelList() {
        return users;
    }
    public MutableLiveData<Boolean> getError() {
        return isError;
    }
    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void getGithubUserModels() {
        githubRepository.getUsers().enqueue(new Callback<List<GithubUserModel>>() {
            @Override
            public void onResponse(Call<List<GithubUserModel>> call, Response<List<GithubUserModel>> response) {

                users.setValue(response.body());
                isError.setValue(false);
                errorMessage.setValue("SUCCESS");
            }

            @Override
            public void onFailure(Call<List<GithubUserModel>> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
                Log.e("ms_error",t.getMessage());
                isError.setValue(true);

            }
        });

    }

}
