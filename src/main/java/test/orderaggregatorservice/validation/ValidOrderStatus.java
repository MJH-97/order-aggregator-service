package test.orderaggregatorservice.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrderStatusValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOrderStatus {
    String message() default "Invalid order status. Must be: PENDING, CONFIRMED, SHIPPED, DELIVERED or CANCELLED";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean caseInsensitive() default true;

    boolean nullable() default false;
}
