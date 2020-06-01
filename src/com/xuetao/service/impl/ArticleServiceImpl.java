package com.xuetao.service.impl;

import com.xuetao.dao.ArticleDao;
import com.xuetao.entity.Article;
import com.xuetao.service.ArticleService;
import lombok.Setter;

import java.util.List;

public class ArticleServiceImpl  implements ArticleService {
    @Setter
    private ArticleDao articleDao;

    @Override
    public List<Article> getAllArticle() {
        List<Article> allArticle = articleDao.getALLArticle();
        return allArticle;
    }
}
