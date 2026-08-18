package test.orderaggregatorservice.helper;

import test.orderaggregatorservice.ErrorMessage;

public record ErrorResult(
        ErrorMessage code,
        String message,
        String timestamp,
        String path
) {
}
