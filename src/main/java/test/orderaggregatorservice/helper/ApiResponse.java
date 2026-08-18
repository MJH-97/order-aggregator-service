package test.orderaggregatorservice.helper;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(boolean success, int status, String errorMessage, T data, String timestamp) {

    public static <T> ApiResponse<T> success(T data, int status){
        return new ApiResponse<>(true, status, null, data, DateHelper.toDateTimeString(LocalDateTime.now()));
    }

    public static <T> ApiResponse<T> failure(String message, int status){
        return new ApiResponse<>(false, status, message, null, DateHelper.toDateTimeString(LocalDateTime.now()));
    }
}
