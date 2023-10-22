package com.example.bridgewelltest.network;

import com.example.bridgewelltest.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("posts")
    Call<List<Post>> getAllPosts();

    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") int id);

    @GET("posts/{id}/comments")
    Call<List<Post>> getPostWithCommentByIds(@Path("id") int id);
}

