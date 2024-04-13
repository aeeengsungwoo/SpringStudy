package com.example.firstproject.dto;
import com.example.firstproject.entity.Article;

public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    //생성자 추가
    public ArticleForm(Long id,String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    //데이터 확인용 코드
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(null,title,content);
    }
}
