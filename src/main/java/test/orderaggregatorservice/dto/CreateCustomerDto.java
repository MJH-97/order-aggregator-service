package test.orderaggregatorservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CreateCustomerDto(
        @NotBlank(message = "Customer name is required")
        @Length(max = 255, message = "Customer name exceeded maximum length of 255")
        String fullName,

        @NotBlank(message = "Customer email is required")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Customer phone is required")
        @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "Invalid phone number")
        String phone
) {
}
