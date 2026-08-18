package test.orderaggregatorservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.orderaggregatorservice.dto.CreateCustomerDto;
import test.orderaggregatorservice.dto.CustomerResponse;
import test.orderaggregatorservice.dto.OrderResponse;
import test.orderaggregatorservice.helper.ApiResponse;
import test.orderaggregatorservice.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomersController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> create(@Valid @RequestBody CreateCustomerDto dto){
        ApiResponse<CustomerResponse> response = customerService.create(dto);
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getCustomerOrders(@PathVariable("customerId") Long customerId){
        ApiResponse<List<OrderResponse>> response = customerService.getCustomerOrders(customerId);
        return ResponseEntity.status(response.status()).body(response);
    }
}
