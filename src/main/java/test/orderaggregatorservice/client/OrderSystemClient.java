package test.orderaggregatorservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import test.orderaggregatorservice.dto.*;
import test.orderaggregatorservice.exception.DownstreamServiceException;
import test.orderaggregatorservice.helper.EndpointUrl;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSystemClient {
    private final WebClient orderSystemClient;

    public CustomerResponse createCustomer(CreateCustomerDto dto){
        return post(dto, EndpointUrl.CREATE_CUSTOMER_ENDPOINT, CustomerResponse.class);
    }

    public OrderResponse createOrder(CreateOrderRequest dto){
        return post(dto, EndpointUrl.CREATE_ORDER_ENDPOINT, OrderResponse.class);
    }

    public OrderResponse updateOrderStatus(UpdateOrderStatusRequest dto, Long orderId){
        return orderSystemClient.put()
                .uri(EndpointUrl.UPDATE_ORDER_STATUS_ENDPOINT, orderId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        res -> res.bodyToMono(ProblemDetail.class)
                                .map(error -> new DownstreamServiceException(error.getDetail(), error.getTitle()))
                )
                .bodyToMono(OrderResponse.class)
                .block();
    }

    public List<OrderResponse> getCustomerOrders(Long customerId){
        return orderSystemClient.get()
                .uri(EndpointUrl.GET_CUSTOMER_ORDERS_ENDPOINT, customerId)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        res -> res.bodyToMono(ProblemDetail.class)
                                .map(error -> new DownstreamServiceException(error.getDetail(), error.getTitle()))
                )
                .bodyToFlux(OrderResponse.class)
                .collectList()
                .block();
    }


    private <T,R> R post(T body, String url, Class<R> responseType){
        return orderSystemClient.post()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        res -> res.bodyToMono(ProblemDetail.class)
                                .map(error -> new DownstreamServiceException(error.getDetail(), error.getTitle()))
                )
                .bodyToMono(responseType)
                .block();
    }
}
