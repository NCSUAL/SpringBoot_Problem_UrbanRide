package lsh.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lsh.security.domain.Branch;
import lsh.security.dto.request.BranchRequest;
import lsh.security.repository.BranchRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public Optional<Branch> createBranch(BranchRequest branchRequest){
        return Optional.of(branchRepository.save(branchRequest.toEntity()));
    }

    public Optional<Branch> findByName(final String name){
        return branchRepository.findByName(name);
    }

    public List<Branch> findAll(){
        return branchRepository.findAll();
    }
}
