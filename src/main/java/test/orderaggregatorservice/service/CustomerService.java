package test.orderaggregatorservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import test.orderaggregatorservice.client.OrderSystemClient;
import test.orderaggregatorservice.dto.CreateCustomerDto;
import test.orderaggregatorservice.dto.CustomerResponse;
import test.orderaggregatorservice.helper.ApiResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final OrderSystemClient orderSystemClient;

    public ApiResponse<CustomerResponse> create(CreateCustomerDto dto){
        log.info("Creating customer: name --> [{}], email --> [{}], phone --> [{}]", dto.fullName(), dto.email(), dto.phone());
        CustomerResponse response = orderSystemClient.createCustomer(dto);
        log.info("Customer [{}] created successfully at [{}]", response.getFullName(), response.getCreatedAt());
        return ApiResponse.success(response, HttpStatus.CREATED.value());
    }
}
