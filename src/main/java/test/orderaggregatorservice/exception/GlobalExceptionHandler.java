package test.orderaggregatorservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import test.orderaggregatorservice.enums.ErrorMessage;
import test.orderaggregatorservice.helper.ApiResponse;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(DownstreamServiceException.class)
    public ResponseEntity<ApiResponse<String>> handleDownstreamServiceException(DownstreamServiceException exception){
        log.error("Failed to connect system service: cause --> [{}], message --> [{}]", exception.getCode(), exception.getMessage());
        String message = "System service error: cause[" + exception.getCode() + "], message[" + exception.getMessage() + "]";
        return ResponseEntity.status(502)
                .body(
                        ApiResponse.failure(message, 502)
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.error("Validation Exception in [{}]: {}", request.getRequestURI(), message);

        return ResponseEntity.badRequest()
                .body(
                        ApiResponse.failure(message, 400)
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
