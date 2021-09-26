package com.ms.hiltmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ms.hiltmvvm.R;
import com.ms.hiltmvvm.io.viewmodel.GithubUserViewModel;
import com.ms.hiltmvvm.ui.adapters.GithubUserAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    GithubUserViewModel githubUserViewModel;
    RecyclerView recyclerUser;
    TextView textViewMessage;
    Button buttonFetchUsers;
    GithubUserAdapter githubUserAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerUser=findViewById(R.id.recyclerUser);
        textViewMessage=findViewById(R.id.textViewMessage);
        buttonFetchUsers=findViewById(R.id.buttonFetchUsers);
        recyclerUser.setHasFixedSize(true);
        recyclerUser.setLayoutManager(new LinearLayoutManager(this));
        githubUserAdapter=new GithubUserAdapter();
        recyclerUser.setAdapter(githubUserAdapter);
        //
        githubUserViewModel = new ViewModelProvider(this).get(GithubUserViewModel.class);
        githubUserViewModel.getGithubUserModelList().observe(this,githubUsersGetDTO -> {
            githubUserAdapter.updateList(githubUsersGetDTO);
        });
        githubUserViewModel.getErrorMessage().observe(this,errorMessage -> {
                textViewMessage.setText(errorMessage);
        });



        buttonFetchUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubUserViewModel.getGithubUserModels();
            }
        });
    }
}