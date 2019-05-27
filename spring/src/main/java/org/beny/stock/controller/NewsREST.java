package org.beny.stock.controller;

import org.beny.stock.dto.news.NewsDetails;
import org.beny.stock.dto.news.NewsList;
import org.beny.stock.dto.news.NewsRequest;
import org.beny.stock.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/rest")
public class NewsREST extends BaseREST {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<?> getNewsList(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) throws RuntimeException {
        return ok(newsService.findAllStream(page, size).map(NewsList::of).collect(toList()));
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<?> getNews(@PathVariable("id") Long id) throws RuntimeException {
        return ok(NewsDetails.of(newsService.findOne(id)));
    }

    @PostMapping("/news")
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsRequest request) throws RuntimeException {
        return ok(NewsDetails.of(newsService.saveAndFlushAdmin(getContext(), request.getNews(getContext()))));
    }

    @PatchMapping("/news/{id}")
    public ResponseEntity<?> updateNews(@PathVariable("id") Long id, @Valid @RequestBody NewsRequest request) throws RuntimeException {
        return ok(NewsDetails.of(newsService.update(getContext(), request.updateNews(newsService.findOne(id)))));
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id) throws RuntimeException {
        newsService.remove(getContext(), id);
        return ok();
    }

}
