package test.orderaggregatorservice.helper;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(boolean success, String errorMessage, T data, String timestamp) {

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(true, null, data, DateHelper.toDateTimeString(LocalDateTime.now()));
    }

    public static <T> ApiResponse<T> failure(String message){
        return new ApiResponse<>(false, message, null, DateHelper.toDateTimeString(LocalDateTime.now()));
    }
}
