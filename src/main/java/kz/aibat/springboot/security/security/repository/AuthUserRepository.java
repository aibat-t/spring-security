package kz.aibat.springboot.security.security.repository;

import kz.aibat.springboot.security.security.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<UserAuth, Long> {

    UserAuth findByEmail(String email);

}
