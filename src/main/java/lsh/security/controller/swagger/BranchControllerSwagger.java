package lsh.security.controller.swagger;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lsh.security.common.UniqueName;
import lsh.security.common.swagger.ApiEntity_Branch;
import lsh.security.domain.Branch;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.request.BranchRequest;

@Tag(description = "Branch(Entity) 관련한 CRUD 작업을 수행. ", name = "Branch")
public interface BranchControllerSwagger {
    
    @Operation(description = "Branch를 생성하는 API 입니다.", summary = "Branch를 생성",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = BranchRequest.class)), description = "description"),
    responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = ApiEntity_Branch.class)))}
    )
    public ApiEntity<Branch> requestCreateBranch(@Valid @UniqueName @RequestBody BranchRequest branchRequest);

    public ApiEntity<List<Branch>> requestFindAllBranch();
}
