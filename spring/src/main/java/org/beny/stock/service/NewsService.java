package org.beny.stock.service;

import org.beny.stock.model.News;
import org.beny.stock.repository.NewsRepository;
import org.beny.stock.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static org.beny.stock.exception.StockError.FORBIDDEN;

@Service
public class NewsService extends BaseService<News, NewsRepository> {

    @Autowired
    private NewsRepository repository;

    public News update(UserContext ctx, News news) {
        if (!ctx.isAdmin() && !ctx.getUserId().equals(news.getUser().getId())) {
            throw FORBIDDEN.exception();
        }
        return saveAndFlush(news);
    }

    @Transactional
    public void remove(UserContext ctx, Long id) {
        News news = findOne(id);
        if (!ctx.isAdmin() && !ctx.getUserId().equals(news.getUser().getId())) {
            throw FORBIDDEN.exception();
        }
        repository.delete(news);
    }

}
