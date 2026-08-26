package test.orderaggregatorservice.exception;

public class DownstreamServiceException extends RuntimeException {
    private final String code;

    public DownstreamServiceException(String message, String code){
        super("Error in system service: " + message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
