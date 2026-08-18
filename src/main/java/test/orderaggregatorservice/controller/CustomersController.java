package test.orderaggregatorservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.orderaggregatorservice.dto.CreateCustomerDto;
import test.orderaggregatorservice.dto.CustomerResponse;
import test.orderaggregatorservice.helper.ApiResponse;
import test.orderaggregatorservice.service.CustomerService;

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
}
