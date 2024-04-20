package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.ToString;


@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(null,title,content);
    }
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
