package com.example.bridgewelltest.repositories;


import com.example.bridgewelltest.models.Post;
import com.example.bridgewelltest.network.ApiClient;
import com.example.bridgewelltest.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {

    private ApiInterface apiInterface;

    public interface FetchPostsCallback {
        void onSuccess(List<Post> posts);
        void onFailure(String errorMessage);
    }

    public PostRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void fetchPosts(FetchPostsCallback callback) {
        Call<List<Post>> call = apiInterface.getAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> postList = response.body();
                    callback.onSuccess(postList);
                } else {
                    callback.onFailure("API call failed");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callback.onFailure("API call failed: " + t.getMessage());
            }
        });
    }
}