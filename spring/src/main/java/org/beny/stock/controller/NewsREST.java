package org.beny.stock.controller;

import org.beny.stock.dto.news.NewsDetails;
import org.beny.stock.dto.news.NewsList;
import org.beny.stock.dto.news.NewsRequest;
import org.beny.stock.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest")
public class NewsREST extends BaseREST {

    @Autowired
    private NewsRepository repository;

    @GetMapping("/news")
    public ResponseEntity<?> getNewsList(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) throws RuntimeException {
        return ok(NewsList.of(repository.findAll(PageRequest.of(page, size, Sort.by("date").descending()))));
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<?> getNews(@PathVariable("id") Long id) throws RuntimeException {
        return ok(NewsDetails.of(repository.findOneById(id)));
    }

    @PostMapping("/news")
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsRequest request) throws RuntimeException {
        return ok(NewsDetails.of(repository.save(request.getNews(getContext()))));
    }

    @PatchMapping("/news/{id}")
    public ResponseEntity<?> updateNews(@PathVariable("id") Long id, @Valid @RequestBody NewsRequest request) throws RuntimeException {
        return ok(NewsDetails.of(repository.save(request.updateNews(repository.findOneById(id)))));
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id) throws RuntimeException {
        repository.delete(repository.findOneById(id));
        return ok();
    }

}
