package test.orderaggregatorservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import test.orderaggregatorservice.client.OrderSystemWebClient;
import test.orderaggregatorservice.dto.CreateOrderRequest;
import test.orderaggregatorservice.dto.OrderResponse;
import test.orderaggregatorservice.dto.UpdateOrderStatusRequest;
import test.orderaggregatorservice.helper.ApiResponse;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderSystemWebClient orderSystemWebClient;

    public ApiResponse<OrderResponse> create(CreateOrderRequest dto){
        log.info("Creating order: product --> [{}], customer --> [{}], quantity --> [{}]", dto.productName(), dto.customerId(), dto.quantity());
        OrderResponse response = orderSystemWebClient.createOrder(dto);
        log.info("Order of product [{}] created successfully at [{}]", response.getProductName(), response.getCreatedAt());
        return ApiResponse.success(response, HttpStatus.CREATED.value());
    }

    public ApiResponse<OrderResponse> updateStatus(UpdateOrderStatusRequest dto, Long orderId){
        dto.setStatus(dto.getStatus().toUpperCase());
        log.info("Updating status of order: id --> [{}], status --> [{}]", orderId, dto.getStatus());
        OrderResponse response = orderSystemWebClient.updateOrderStatus(dto, orderId);
        log.info("Order [{}] updated to status [{}] successfully", response.getId(), response.getStatus());
        return ApiResponse.success(response, HttpStatus.OK.value());
    }
}
