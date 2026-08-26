package test.orderaggregatorservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import test.orderaggregatorservice.client.OrderSystemWebClient;
import test.orderaggregatorservice.dto.CreateCustomerDto;
import test.orderaggregatorservice.dto.CustomerResponse;
import test.orderaggregatorservice.dto.OrderResponse;
import test.orderaggregatorservice.helper.ApiResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final OrderSystemWebClient orderSystemWebClient;

    public ApiResponse<CustomerResponse> create(CreateCustomerDto dto){
        log.info("Creating customer: name --> [{}], email --> [{}], phone --> [{}]", dto.fullName(), dto.email(), dto.phone());
        CustomerResponse response = orderSystemWebClient.createCustomer(dto);
        log.info("Customer [{}] created successfully at [{}]", response.getFullName(), response.getCreatedAt());
        return ApiResponse.success(response, HttpStatus.CREATED.value());
    }

    public ApiResponse<List<OrderResponse>> getCustomerOrders(Long customerId){
        log.info("Fetching orders for customer [{}] .......", customerId);
        List<OrderResponse> response = orderSystemWebClient.getCustomerOrders(customerId);
        log.info("Returned [{}] orders for customer [{}]", response.size(), customerId);
        return ApiResponse.success(response, HttpStatus.OK.value());
    }
}
