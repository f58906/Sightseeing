package com.example.bridgewelltest.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bridgewelltest.models.Post;
import com.example.bridgewelltest.repositories.PostRepository;

import java.util.List;

public class PostViewModel extends ViewModel {

    private PostRepository postRepository;
    private MutableLiveData<List<Post>> postList;

    public PostViewModel() {
        postRepository = new PostRepository();
        postList = new MutableLiveData<>();
    }

    public LiveData<List<Post>> getPostList() {
        return postList;
    }

    public void fetchPosts() {
        postRepository.fetchPosts(new PostRepository.FetchPostsCallback() {
            @Override
            public void onSuccess(List<Post> posts) {
                postList.setValue(posts);
            }

            @Override
            public void onFailure(String errorMessage) {
            }
        });
    }
}