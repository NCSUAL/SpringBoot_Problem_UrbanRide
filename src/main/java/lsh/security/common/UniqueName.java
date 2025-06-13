package lsh.security.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lsh.security.common.validator.UniqueNameValidator;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNameValidator.class)
public @interface UniqueName {

    String message() default "UniqueName Validation Exception";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
