package kz.aibat.springboot.security.security.service;

import kz.aibat.springboot.security.security.model.UserAuth;
import kz.aibat.springboot.security.security.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user = authUserRepository.findByEmail(username);

        if(user != null) return user;
        throw new UsernameNotFoundException("User not found");
    }

}
