package test.orderaggregatorservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OrderSystemClient {
    private WebClient orderSystemClient;
}
