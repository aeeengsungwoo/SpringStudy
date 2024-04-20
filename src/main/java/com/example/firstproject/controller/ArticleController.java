package com.example.firstproject.controller;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String NewArticlesForm(){
        return "articles/new";
    }

    @PostMapping ("/articles/create")
    public String createArticles(ArticleForm form){
        log.info(form.toString());
        //System.out.println(form.toString()); log로 대체

        Article article = form.toEntity(); //DTO를 entity로 변환
        log.info(article.toString());
        //System.out.println(article.toString()); log로 대체

        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString()); log로 대체
        return "redirect：/articles/" + saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
    log.info("id = " + id );
    // 1. id를 조회해 데이터 가져오기
        // Optional<Article> articleEntity = articleRepository.findById(id);도 가능하다.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // id가 없으면 null을 반환한다.
    // 2. 모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
    // 3. 뷰 페이지 반환하기
    return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        // 1 . 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
            // 정확히 하려면 ArrayList<Article>이 맞지만, ArrayList가 List를 포함하기 때문에 가능하다.
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }
}
