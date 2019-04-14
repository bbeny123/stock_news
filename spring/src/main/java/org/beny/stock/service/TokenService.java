package org.beny.stock.service;

import org.beny.stock.exception.StockException;
import org.beny.stock.model.Token;
import org.beny.stock.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.beny.stock.exception.StockError.TOKEN_NOT_EXISTS;

@Service
public class TokenService extends BaseService<Token, TokenRepository> {

    @Autowired
    private TokenRepository repository;

    public Token findByToken(String token) throws StockException {
        return repository
                .findByToken(token)
                .orElseThrow(TOKEN_NOT_EXISTS::exception);
    }

}
