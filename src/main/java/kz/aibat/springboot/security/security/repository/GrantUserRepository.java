package kz.aibat.springboot.security.security.repository;

import kz.aibat.springboot.security.security.model.GrantAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrantUserRepository extends JpaRepository<GrantAccess, Long> {
    GrantAccess findByAccessValue(String accessValue);
}
