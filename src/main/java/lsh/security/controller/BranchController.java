package lsh.security.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lsh.security.domain.Branch;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.request.BranchRequest;
import lsh.security.dto.request.BranchUpdateRequest;
import lsh.security.service.branch.BranchService;
import lsh.security.swagger.controller.BranchControllerSwagger;
import java.util.List;

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
    public ApiEntity<List<Branch>> requestFindAllBranch(){
        return ApiEntity.ok(branchService.findAll());
    }

    @Override
    @RequestMapping(method=RequestMethod.POST)
    public ApiEntity<Branch> requestCreateBranch(@Valid @RequestBody BranchRequest branchRequest) {
        return ApiEntity.ok(branchService.createBranch(branchRequest).orElseThrow(() -> new UnsupportedOperationException("구현중입니다.")));
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ApiEntity<Branch> requestUpdateBranch(@Valid @RequestBody BranchUpdateRequest branchUpdateRequest){
        return ApiEntity.ok(branchService.isPresent(branchUpdateRequest.id(),present ->  branchService.updateByBranchUpdateRequest(branchUpdateRequest)));
    }

    @Override
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ApiEntity<String> requestDeleteBranch(@PathVariable(value = "id") final Long id){
        branchService.isPresent(id,present -> branchService.delete(id));
        return ApiEntity.ok("해당 엔티티를 삭제했습니다.");
    }
    
}
