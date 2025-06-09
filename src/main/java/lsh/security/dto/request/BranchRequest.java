package lsh.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import lsh.security.common.annotaion.EnumConverter;
import lsh.security.constant.CityType;
import lsh.security.domain.Branch;

public record BranchRequest(
    @NotBlank(message = "[에러] null/빈 값이 아니어야 합니다.") String name,
    @EnumConverter CityType cityType) 
{

        public Branch toEntity(){
            return Branch.builder()
            .name(name)
            .cityType(cityType)
            .build();
        }
}
