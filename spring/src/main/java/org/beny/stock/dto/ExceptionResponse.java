package org.beny.stock.dto;

import lombok.Data;
import org.beny.stock.exception.StockException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Data
public class ExceptionResponse {

    private String message;
    private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private int stockCode = 500;

    public ExceptionResponse(StockException ex) {
        this.message = ex.getMessage();
        this.code = ex.getHttpCode();
        this.stockCode = ex.getErrorCode();
    }

    public ExceptionResponse(MethodArgumentNotValidException ex) {
        this.message = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .findFirst()
                .map(e -> (ex.getBindingResult().getFieldError() != null
                        ? ex.getBindingResult().getFieldError().getField() + " "
                        : "")
                        + e.getDefaultMessage())
                .orElseGet(ex::getLocalizedMessage);
    }

    public ExceptionResponse(Exception ex) {
        this.message = ex.getMessage();
    }
}
