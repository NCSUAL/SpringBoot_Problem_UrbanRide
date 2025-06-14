package lsh.security.common.validator;

import java.beans.Introspector;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lsh.security.common.UniqueColumn;
import lsh.security.domain.Branch;
import lsh.security.service.UniqueColumnService;

@Component
@RequiredArgsConstructor
public class UniqueColumnValidator implements ConstraintValidator<UniqueColumn, String>{

    private final Map<String, UniqueColumnService> nameServices;
    
    private UniqueColumnService targetNameService;

    @Override
    public void initialize(UniqueColumn uniqueName) {
        targetNameService = nameServices.get(Introspector.decapitalize(uniqueName.service().getSimpleName()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<Branch> branch = targetNameService.findByName(value);

        context.disableDefaultConstraintViolation();
        
        context.buildConstraintViolationWithTemplate("이미 등록된 name이 있습니다.").addConstraintViolation();
        return branch.isEmpty();
    }

}
