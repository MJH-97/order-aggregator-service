package test.orderaggregatorservice.helper;

import test.orderaggregatorservice.enums.ErrorMessage;

public record ErrorResult(
        ErrorMessage code,
        String message,
        String timestamp,
        String path
) {
}
