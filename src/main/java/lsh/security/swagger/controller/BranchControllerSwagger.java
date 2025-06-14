package lsh.security.swagger.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lsh.security.domain.Branch;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.request.BranchRequest;
import lsh.security.dto.request.BranchUpdateRequest;

@Tag(description = "Branch(Entity) 관련한 CRUD 작업을 수행. ", name = "Branch")
public interface BranchControllerSwagger {
    
    @Operation(description = "Branch를 생성하는 API 입니다.", summary = "Branch를 생성",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = BranchRequest.class)),
     description = "[name]: String,\n [cityName]: Enum(String)")
    )
    public ApiEntity<Branch> requestCreateBranch(@Valid @RequestBody BranchRequest branchRequest);

    @Operation(description = "Branch를 전부 조회하는 API 입니다.", summary = "Branch를 전부 조회")
    public ApiEntity<List<Branch>> requestFindAllBranch();

    @Operation(description = "특정 Branch를 수정하는 API 입니다.", summary = "특정 Branch 수정")
    public ApiEntity<Branch> requestUpdateBranch(@Valid @RequestBody BranchUpdateRequest branchUpdateRequest);

    @Operation(description = "특정 Branch를 삭제하는 API 입니다.", summary = "특정 Branch를 삭제")
    public ApiEntity<String> requestDeleteBranch(@PathVariable(value = "id") final Long id);
}
