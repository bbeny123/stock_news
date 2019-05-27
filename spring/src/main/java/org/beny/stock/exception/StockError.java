package org.beny.stock.exception;

import org.springframework.http.HttpStatus;

public enum StockError {

    FORBIDDEN(0, "Forbidden", HttpStatus.FORBIDDEN),
    UNAUTHORIZED(1, "Unauthorized", HttpStatus.UNAUTHORIZED),
    CAPTCHA_ERROR(2, "Captcha Error", HttpStatus.FORBIDDEN),
    LOGIN_EXISTS(3, "Login is already in use", HttpStatus.CONFLICT),
    EMAIL_EXISTS(4, "The e-mail address is already in use", HttpStatus.CONFLICT),
    LOGIN_NOT_EXISTS(5, "Login does not exist in database", HttpStatus.NOT_FOUND),
    EMAIL_NOT_EXISTS(6, "The e-mail does not exist in database", HttpStatus.NOT_FOUND),
    ITEM_NOT_EXISTS(7, "The item does not exist in database", HttpStatus.NOT_FOUND),
    TOKEN_NOT_EXISTS(8, "The token does not exist in database", HttpStatus.NOT_FOUND),
    USER_ALREADY_ACTIVE(9, "User connected with this email is already active", HttpStatus.CONFLICT),

    INTERNAL_SERVER_ERROR(500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private int code;

    private String message;

    private HttpStatus httpStatus;

    StockError(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public StockException exception() {
        return new StockException(this);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
