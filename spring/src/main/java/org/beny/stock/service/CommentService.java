package org.beny.stock.service;

import org.beny.stock.model.Comment;
import org.beny.stock.model.News;
import org.beny.stock.repository.CommentRepository;
import org.beny.stock.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static org.beny.stock.exception.StockError.FORBIDDEN;

@Service
public class CommentService extends BaseService<Comment, CommentRepository> {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private NewsService newsService;

    public Comment create(UserContext ctx, Comment comment, Long newsId) {
        News news = newsService.findOne(newsId);
        news.getComments().add(comment);
        newsService.save(news);
        return comment;
    }

    public Comment update(UserContext ctx, Comment comment) {
        if (!ctx.isAdmin() && !ctx.getUserId().equals(comment.getUser().getId())) {
            throw FORBIDDEN.exception();
        }
        return saveAndFlush(comment);
    }

    @Transactional
    public void remove(UserContext ctx, Long id) {
        Comment comment = findOne(id);
        if (!ctx.isAdmin() && !ctx.getUserId().equals(comment.getUser().getId())) {
            throw FORBIDDEN.exception();
        }
        repository.delete(comment);
    }

}
