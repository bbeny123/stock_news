package org.beny.stock.dto;

import org.beny.stock.exception.StockException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ExceptionResponse {

    private String message;
    private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private int stockCode = 500;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStockCode() {
        return stockCode;
    }

    public void setStockCode(int stockCode) {
        this.stockCode = stockCode;
    }

    public static ExceptionResponse of(StockException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.message = ex.getMessage();
        response.code = ex.getHttpCode();
        response.stockCode = ex.getErrorCode();
        return response;
    }

    public static ExceptionResponse of(MethodArgumentNotValidException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.message = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .findFirst()
                .map(e -> (ex.getBindingResult().getFieldError() != null
                        ? ex.getBindingResult().getFieldError().getField() + " "
                        : "")
                        + e.getDefaultMessage())
                .orElseGet(ex::getLocalizedMessage);
        return response;
    }

    public static ExceptionResponse of(Exception ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.message = ex.getMessage();
        return response;
    }
}
