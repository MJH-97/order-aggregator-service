package test.orderaggregatorservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import test.orderaggregatorservice.enums.OrderStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OrderStatusValidator implements ConstraintValidator<ValidOrderStatus, String> {

    private boolean caseInsensitive;

    @Override
    public void initialize(ValidOrderStatus constraintAnnotation) {
        this.caseInsensitive = constraintAnnotation.caseInsensitive();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String validValues = Arrays.stream(OrderStatus.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));

        boolean isValid = false;
        String normalizedValue = caseInsensitive ? value.trim().toUpperCase() : value.trim();

        for (OrderStatus status : OrderStatus.values()) {
            if (caseInsensitive) {
                if (status.name().equalsIgnoreCase(value.trim())) {
                    isValid = true;
                    break;
                }
            } else {
                if (status.name().equals(value.trim())) {
                    isValid = true;
                    break;
                }
            }
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    String.format("Invalid order status: '%s'. Valid values are: %s",value, validValues)
            ).addConstraintViolation();
        }

        return isValid;
    }
}
