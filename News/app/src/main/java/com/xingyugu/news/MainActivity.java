package com.xingyugu.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.xingyugu.news.model.GetArticlesResponse;
import com.xingyugu.news.newworking.NewsAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView newsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        newsRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerview);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        Call<GetArticlesResponse> call = NewsAPI.getApi().getArticles("espn", "top");
        call.enqueue(new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(Call<GetArticlesResponse> call, Response<GetArticlesResponse> response) {
                GetArticlesResponse getArticlesResponse = response.body();
                NewsStore.setNewsArticles(getArticlesResponse.getArticles());
                Toast.makeText(MainActivity.this, "Response received", Toast.LENGTH_SHORT).show();
                HomeNewsAdapter homeNewsAdapter = new HomeNewsAdapter(getArticlesResponse.getArticles());
                newsRecyclerView.setAdapter(homeNewsAdapter);
            }

            @Override
            public void onFailure(Call<GetArticlesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error Received", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
