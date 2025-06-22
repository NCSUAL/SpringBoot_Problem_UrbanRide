package lsh.security.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lsh.security.domain.Branch;
import lsh.security.dto.request.BranchRequest;
import lsh.security.exception.NotFoundEntityException;
import lsh.security.repository.BranchRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchService implements UniqueColumnService{

    private final BranchRepository branchRepository;

    public Optional<Branch> insertBranch(BranchRequest branchRequest){
        return Optional.of(branchRepository.save(branchRequest.toEntity()));
    }

    public Optional<Branch> inquiryWithID(final Long id){
        return branchRepository.findById(id);
    }

    @Override
    public Optional<Branch> InquiryWithUniqueName(final String name){
        return branchRepository.findByName(name);
    }

    public List<Branch> inquiryAll(){
        return branchRepository.findAll();
    }
    
    public Branch updateByBranchRequest(final Branch branch, final BranchRequest branchRequest){
        return branchRepository.save(branch.update(branchRequest));
    }
    
    public Branch patchByBranchRequest(final Branch branch,final BranchRequest branchRequest){
        return branchRepository.save(branch.patch(branchRequest));
    }

    public void delete(final Long id){
        branchRepository.deleteById(id);
    }

    public void delete(final Branch branch){
        branchRepository.delete(branch);
    }

    public Branch isPresent(final Long id){
        return inquiryWithID(id).orElseThrow( () -> new NotFoundEntityException(HttpStatus.BAD_REQUEST, "해당 엔티티는 존재하지 않습니다."));
    }
    
    public Branch isPresent(final Long id, Consumer<? super Branch> Consumer){
        Optional<Branch> branch = inquiryWithID(id);
        inquiryWithID(id).ifPresentOrElse(Consumer,() -> {throw new NotFoundEntityException(HttpStatus.BAD_REQUEST, "해당 엔티티는 존재하지 않습니다.");});
        return branch.get();
    }
}
