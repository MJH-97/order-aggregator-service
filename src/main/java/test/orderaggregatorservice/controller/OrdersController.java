package test.orderaggregatorservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.orderaggregatorservice.dto.CreateOrderRequest;
import test.orderaggregatorservice.dto.OrderResponse;
import test.orderaggregatorservice.dto.UpdateOrderStatusRequest;
import test.orderaggregatorservice.helper.ApiResponse;
import test.orderaggregatorservice.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrdersController{
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> create(@Valid @RequestBody CreateOrderRequest dto){
        ApiResponse<OrderResponse> response = orderService.create(dto);
        return ResponseEntity.status(response.status()).body(response);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<ApiResponse<OrderResponse>> updateStatus(@Valid @RequestBody UpdateOrderStatusRequest dto, @PathVariable("orderId") Long orderId){
        ApiResponse<OrderResponse> response = orderService.updateStatus(dto, orderId);
        return ResponseEntity.status(response.status()).body(response);
    }
}
