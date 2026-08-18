package test.orderaggregatorservice.helper;

public class EndpointUrl {
    public static final String CREATE_CUSTOMER_ENDPOINT = "/internal/customers";
    public static final String CREATE_ORDER_ENDPOINT = "/internal/orders";
    public static final String UPDATE_ORDER_STATUS_ENDPOINT = "/internal/orders/{orderId}/status";
    public static final String GET_CUSTOMER_ORDERS_ENDPOINT = "/internal/customers/{customerId}/orders";
}
