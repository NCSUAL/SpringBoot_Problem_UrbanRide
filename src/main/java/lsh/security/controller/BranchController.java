package lsh.security.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lsh.security.common.validator.crud.Create;
import lsh.security.common.validator.crud.Patch;
import lsh.security.common.validator.crud.Update;
import lsh.security.domain.Branch;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.request.BranchRequest;
import lsh.security.dto.response.BranchResponse;
import lsh.security.service.BranchService;
import lsh.security.swagger.controller.BranchControllerSwagger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/branch")
public class BranchController implements BranchControllerSwagger {

    private final BranchService branchService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public ApiEntity<List<BranchResponse>> requestInquiryAllBranch(){
        return ApiEntity.ok(branchService.inquiryAll().stream()
        .map(arg -> BranchResponse.of(arg))
        .collect(Collectors.toList()));
    }

    @Override
    @RequestMapping(method=RequestMethod.POST)
    public ApiEntity<BranchResponse> requestInsertBranch(@JsonView(Create.class) @Validated(Create.class) @RequestBody BranchRequest branchRequest) {
        return ApiEntity.ok(
            BranchResponse.of(branchService.insertBranch(branchRequest)
            .orElseThrow(() -> new UnsupportedOperationException("구현중입니다.")
        )));
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ApiEntity<BranchResponse> requestUpdateBranch(@JsonView(Update.class) @Validated(Update.class) @RequestBody BranchRequest branchUpdateRequest){
        return ApiEntity.ok(
            BranchResponse.of(branchService.isPresent(branchUpdateRequest.id(),present ->  branchService.updateByBranchRequest(present, branchUpdateRequest))));
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ApiEntity<BranchResponse> requestPatchBranch(@PathVariable(value = "id") final Long id, @JsonView(Patch.class) @Validated(Patch.class) @RequestBody BranchRequest branchPatchRequest){
        return ApiEntity.ok(BranchResponse.of(branchService.isPresent(id,present -> branchService.patchByBranchRequest(present, branchPatchRequest))));
    }

    @Override
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ApiEntity<String> requestDeleteBranch(@PathVariable(value = "id") final Long id){
        branchService.isPresent(id,present -> branchService.delete(id));
        return ApiEntity.ok("해당 엔티티를 삭제했습니다.");
    }
    
}
