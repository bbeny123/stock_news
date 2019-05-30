package org.beny.stock.service;

import org.beny.stock.exception.StockException;
import org.beny.stock.model.Comment;
import org.beny.stock.model.News;
import org.beny.stock.model.User;
import org.beny.stock.repository.CommentRepository;
import org.beny.stock.repository.NewsRepository;
import org.beny.stock.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.beny.stock.exception.StockError.FORBIDDEN;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private NewsRepository newsRepository;

    public Comment create(Comment comment, Long newsId) throws StockException {
        News news = newsRepository.findOneById(newsId);
        news.addComment(comment);
        newsRepository.save(news);
        return comment;
    }

    public Comment update(UserContext ctx, Comment comment) throws StockException {
        checkIfAdminOrOwner(ctx, comment.getUser());
        return repository.save(comment);
    }

    public void remove(UserContext ctx, Long id) throws StockException {
        Comment comment = repository.findOneById(id);
        checkIfAdminOrOwner(ctx, comment.getUser());
        repository.delete(comment);
    }

    private void checkIfAdminOrOwner(UserContext ctx, User owner) throws StockException {
        if (!ctx.isAdmin() && !ctx.getUserId().equals(owner.getId())) {
            throw FORBIDDEN.exception();
        }
    }

}
