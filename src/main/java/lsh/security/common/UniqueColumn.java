package lsh.security.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lsh.security.common.validator.UniqueColumnValidator;

@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueColumnValidator.class)
public @interface UniqueColumn {

    Class<?> service();

    String message() default "UniqueColumn Validation Exception";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
