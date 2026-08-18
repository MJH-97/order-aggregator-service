package test.orderaggregatorservice.exception;

import test.orderaggregatorservice.ErrorMessage;

public class DownstreamServiceException extends RuntimeException {
    private final ErrorMessage code;

    public DownstreamServiceException(String message, ErrorMessage code){
        super("Error in system service: " + message);
        this.code = code;
    }

    public ErrorMessage getCode() {
        return code;
    }
}
