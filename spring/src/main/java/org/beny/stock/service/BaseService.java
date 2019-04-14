package org.beny.stock.service;


import org.beny.stock.exception.StockException;
import org.beny.stock.repository.BaseRepository;
import org.beny.stock.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

import static org.beny.stock.exception.StockError.FORBIDDEN;
import static org.beny.stock.exception.StockError.ITEM_NOT_EXISTS;

public abstract class BaseService<T, U extends BaseRepository<T>> {

    @Autowired
    private U repository;

    @Transactional
    protected void save(T data) {
        repository.save(data);
    }

    @Transactional
    protected T saveAndFlush(T data) {
        return repository.saveAndFlush(data);
    }

    @Transactional
    protected void saveAdmin(UserContext ctx, T data) throws StockException {
        checkAdmin(ctx);
        save(data);
    }

    @Transactional
    protected T saveAndFlushAdmin(UserContext ctx, T data) throws StockException {
        checkAdmin(ctx);
        return saveAndFlush(data);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAllAdmin(UserContext ctx) throws StockException {
        checkAdmin(ctx);
        return findAll();
    }

    public T findOne(Long id) throws StockException {
        return repository.findById(id)
                .orElseThrow(ITEM_NOT_EXISTS::exception);
    }

    public T findOneAdmin(UserContext ctx, Long id) throws StockException {
        checkAdmin(ctx);
        return findOne(id);
    }

    protected void checkAdmin(UserContext ctx) throws StockException {
        if (!ctx.isAdmin()) {
            throw FORBIDDEN.exception();
        }
    }

}
