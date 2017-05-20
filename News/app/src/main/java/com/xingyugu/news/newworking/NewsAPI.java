package com.xingyugu.news.newworking;

import com.xingyugu.news.model.GetArticlesResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by XingyuGU on 5/19/17.
 */

public class NewsAPI {
    private static final String APIKEY = "06f9af80a73d4f649dfbb24720fd8094";
    private static final String APIPATH = "http://newsapi.org/v1/";

    private static NewsService newsService = null;

    public static NewsService getApi(){
        if(newsService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIPATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsService = retrofit.create(NewsService.class);
        }
        return newsService;
    }

    public interface NewsService{
        @GET("articles?apiKey=" + APIKEY)
        Call<GetArticlesResponse> getArticles(@Query("source") String source, @Query("sortBy") String sortBy);
    }
}
