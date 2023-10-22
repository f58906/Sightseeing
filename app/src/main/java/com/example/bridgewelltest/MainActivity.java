package com.example.bridgewelltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bridgewelltest.adapter.PostAdapter;
import com.example.bridgewelltest.models.Post;
import com.example.bridgewelltest.viewmodels.PostViewModel;

import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostViewModel postViewModel;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);

        // 取得 ViewModel
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);

        // 觀察 ViewModel 中的資料
        postViewModel.getPostList().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                postAdapter = new PostAdapter(posts);
                recyclerView.setAdapter(postAdapter);
            }
        });

        // 呼叫 fetchPosts() 以獲取資料
        postViewModel.fetchPosts();
    }
}