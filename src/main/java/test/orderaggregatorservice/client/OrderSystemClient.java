package test.orderaggregatorservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import test.orderaggregatorservice.dto.CreateCustomerDto;
import test.orderaggregatorservice.dto.CustomerResponse;
import test.orderaggregatorservice.exception.DownstreamServiceException;
import test.orderaggregatorservice.helper.EndpointUrl;
import test.orderaggregatorservice.helper.ErrorResult;

@Service
@RequiredArgsConstructor
public class OrderSystemClient {
    private final WebClient orderSystemClient;

    public CustomerResponse createCustomer(CreateCustomerDto dto){
        return orderSystemClient.post()
                .uri(EndpointUrl.CREATE_CUSTOMER_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        res -> res.bodyToMono(ErrorResult.class)
                                .map(error -> new DownstreamServiceException(error.message(), error.code()))
                )
                .bodyToMono(CustomerResponse.class)
                .block();
    }
}
