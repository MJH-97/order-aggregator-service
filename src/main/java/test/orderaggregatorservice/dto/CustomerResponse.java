package test.orderaggregatorservice.dto;

import lombok.Data;

@Data
public class CustomerResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String createdAt;
}
