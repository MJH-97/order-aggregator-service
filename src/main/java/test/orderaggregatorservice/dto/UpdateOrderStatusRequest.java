package test.orderaggregatorservice.dto;

import jakarta.validation.constraints.NotNull;
import test.orderaggregatorservice.enums.OrderStatus;

public record UpdateOrderStatusRequest(
        @NotNull(message = "Status is required")
        OrderStatus status
) {
}