package lsh.security.service.branch;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lsh.security.domain.Branch;
import lsh.security.dto.request.BranchRequest;
import lsh.security.dto.request.BranchUpdateRequest;
import lsh.security.exception.NotFoundEntityException;
import lsh.security.repository.BranchRepository;
import lsh.security.service.UniqueColumnService;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchService implements UniqueColumnService{

    private final BranchRepository branchRepository;

    public Optional<Branch> createBranch(BranchRequest branchRequest){
        return Optional.of(branchRepository.save(branchRequest.toEntity()));
    }

    @Override
    public Optional<Branch> findByName(final String name){
        return branchRepository.findByName(name);
    }

    public List<Branch> findAll(){
        return branchRepository.findAll();
    }

    public void delete(final Long id){
        branchRepository.deleteById(id);
    }

    public void delete(final Branch branch){
        branchRepository.delete(branch);
    }
    
    public Optional<Branch> findById(final Long id){
        return branchRepository.findById(id);
    }

    public Branch updateByBranchUpdateRequest(final BranchUpdateRequest branchUpdateRequest){
        return branchRepository.save(branchUpdateRequest.toEntity());
    }

    public Branch isPresent(final Long id, Consumer<? super Branch> Consumer){
        Optional<Branch> branch = findById(id);
        branch.ifPresentOrElse(Consumer,() -> {throw new NotFoundEntityException(HttpStatus.BAD_REQUEST, "해당 엔티티는 존재하지 않습니다.");});
        return branch.get();
    }
}
