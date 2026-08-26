package test.orderaggregatorservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import test.orderaggregatorservice.validation.ValidOrderStatus;

@Data
public class UpdateOrderStatusRequest{
        @NotNull(message = "Status is required")
        @ValidOrderStatus
        private String status;
}