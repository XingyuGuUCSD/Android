package com.xingyugu.news;

import com.xingyugu.news.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XingyuGU on 5/19/17.
 */

public class NewsStore {
    private static List<Article> newsArticles = new ArrayList<>();

    public static List<Article> getNewsArticles() {
        return newsArticles;
    }

    public static void setNewsArticles(List<Article> newsArticles) {
        NewsStore.newsArticles = newsArticles;
    }
}
