package lsh.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lsh.security.domain.user.User;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);
}
