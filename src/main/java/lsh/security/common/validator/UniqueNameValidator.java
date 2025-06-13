package lsh.security.common.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lsh.security.common.UniqueName;
import lsh.security.domain.Branch;
import lsh.security.dto.request.BranchRequest;
import lsh.security.service.BranchService;

@Component
@RequiredArgsConstructor
public class UniqueNameValidator implements ConstraintValidator<UniqueName, BranchRequest>{

    @Autowired
    private final BranchService branchService;

    @Override
    public boolean isValid(BranchRequest value, ConstraintValidatorContext context) {
        Optional<Branch> branch = branchService.findByName(value.name());

        context.disableDefaultConstraintViolation();
        
        context.buildConstraintViolationWithTemplate("이미 등록된 name이 있습니다.").addConstraintViolation();
        return branch.isEmpty();
    }

}
