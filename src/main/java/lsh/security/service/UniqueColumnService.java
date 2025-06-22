package lsh.security.service;

import java.util.Optional;

import lsh.security.domain.Branch;

public interface UniqueColumnService {

    public Optional<Branch> InquiryWithUniqueName(final String name);
} 
