package lsh.security.dto.request;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lsh.security.common.UniqueColumn;
import lsh.security.common.validator.crud.Create;
import lsh.security.common.validator.crud.Patch;
import lsh.security.common.validator.crud.Update;
import lsh.security.constant.nested.CityType;
import lsh.security.domain.Branch;
import lsh.security.service.BranchService;

public record BranchRequest(
    @Null(groups = Patch.class)
    @NotNull(groups = Update.class, message = "빈 값이 아니어야 합니다.")
    @JsonView(Update.class)
    Long id,

    @UniqueColumn(service = BranchService.class, groups = {Update.class, Create.class, Patch.class}) 
    @NotBlank(message = "[에러] null/빈 값이 아니어야 합니다.", groups = {Create.class, Update.class})
    @JsonView({Update.class, Create.class, Patch.class})
    String name,

    @JsonView({Update.class, Create.class, Patch.class})
    CityType cityName) 
{
        public Branch toEntity(){
            return Branch.builder()
            .id(id)
            .name(name)
            .cityType(cityName)
            .build();
        }
}
