package org.beny.stock.exception;

public class StockException extends RuntimeException {

    private final StockError error;

    public StockException(StockError error) {
        super(error.getMessage());
        this.error = error;
    }

    public int getErrorCode() {
        return error.getCode();
    }

    public int getHttpCode() {
        return error.getHttpStatus().value();
    }

    @Override
    public String toString() {
        return error.getCode() + ": " + error.getMessage();
    }

}
