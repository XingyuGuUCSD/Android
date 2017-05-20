package com.xingyugu.news;

import com.xingyugu.news.model.NewsArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XingyuGU on 5/19/17.
 */

public class NewsStore {
    private static List<NewsArticle> newsArticles = new ArrayList<>();

    public static List<NewsArticle> getNewsArticles() {
        return newsArticles;
    }

    public static void setNewsArticles(List<NewsArticle> newsArticles) {
        NewsStore.newsArticles = newsArticles;
    }
}
