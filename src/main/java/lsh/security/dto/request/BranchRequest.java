package lsh.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import lsh.security.constant.nested.CityType;
import lsh.security.domain.Branch;

public record BranchRequest(
    @NotBlank(message = "[에러] null/빈 값이 아니어야 합니다.") String name,
    CityType cityName) 
{
        public Branch toEntity(){
            return Branch.builder()
            .name(name)
            .cityType(cityName)
            .build();
        }
}
