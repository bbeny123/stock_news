package org.beny.stock.controller;

import org.apache.logging.log4j.Logger;
import org.beny.stock.dto.ExceptionResponse;
import org.beny.stock.exception.StockException;
import org.beny.stock.security.ContextHolder;
import org.beny.stock.security.UserContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public abstract class BaseREST {

    private final Logger logger = getLogger(this.getClass());

    @ExceptionHandler(StockException.class)
    public ResponseEntity<?> stockException(StockException ex) {
        logger.warn(ex.getMessage());
        return ResponseEntity.status(ex.getHttpCode()).body(new ExceptionResponse(ex));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validException(MethodArgumentNotValidException ex) {
        logger.warn(ex.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(new ExceptionResponse(ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception ex) {
        logger.warn(ex.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ExceptionResponse(ex));
    }

    protected UserContext getContext() {
        return ContextHolder.getUserContext();
    }

    protected ResponseEntity<?> ok() {
        return ResponseEntity.ok().build();
    }

    protected <T> ResponseEntity<T> ok(T t) {
        return ResponseEntity.ok(t);
    }

}
