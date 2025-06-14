package lsh.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import lsh.security.common.UniqueColumn;
import lsh.security.constant.nested.CityType;
import lsh.security.domain.Branch;
import lsh.security.service.branch.BranchService;

public record BranchUpdateRequest(
    Long id,
    @UniqueColumn(service = BranchService.class) @NotBlank(message = "[에러] null/빈 값이 아니어야 합니다.")
    String name,
    CityType cityName
) {        
        public Branch toEntity(){
            return Branch.builder()
            .id(id)
            .cityType(cityName)
            .name(name)
            .build();
        }
} 
