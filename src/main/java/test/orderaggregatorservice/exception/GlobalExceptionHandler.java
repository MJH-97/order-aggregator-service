package test.orderaggregatorservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import test.orderaggregatorservice.ErrorMessage;
import test.orderaggregatorservice.helper.ApiResponse;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(DownstreamServiceException.class)
    public ResponseEntity<ApiResponse<String>> handleDownstreamServiceException(DownstreamServiceException exception){
        log.error("Failed to connect system service: cause --> [{}], message --> [{}]", exception.getCode().name(), exception.getMessage());
        String message = "System service error: cause[" + exception.getCode().name() + "], message[" + exception.getMessage() + "]";
        return ResponseEntity.status(502)
                .body(
                        ApiResponse.failure(message, 502)
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericExceptions(Exception exception, HttpServletRequest request){
        log.error("Unexpected Exception in [{}]: {}", request.getRequestURI(), exception.getMessage());
        return ResponseEntity.internalServerError()
                .body(
                        ApiResponse.failure(ErrorMessage.INTERNAL_ERROR.name(), 500)
                );
    }
}
