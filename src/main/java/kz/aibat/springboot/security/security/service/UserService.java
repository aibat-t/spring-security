package kz.aibat.springboot.security.security.service;

import kz.aibat.springboot.security.security.model.GrantAccess;
import kz.aibat.springboot.security.security.model.UserAuth;
import kz.aibat.springboot.security.security.repository.AuthUserRepository;
import kz.aibat.springboot.security.security.repository.GrantUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;
    private final GrantUserRepository grantUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user = authUserRepository.findByEmail(username);

        if(user != null) return user;
        throw new UsernameNotFoundException("User not found");
    }

    public UserAuth createUser(UserAuth userAuth){
        GrantAccess userAccess = grantUserRepository.findByAccessValue("ROLE_USER");
        List<GrantAccess> grantAccessList = new ArrayList<>();
        grantAccessList.add(userAccess);
        userAuth.setGrantAccessList(grantAccessList);

        return authUserRepository.save(userAuth);
    }

    public UserAuth getUserByEmail(String email){
        return authUserRepository.findByEmail(email);
    }

    public UserAuth updateUser(UserAuth user){
        return authUserRepository.save(user);
    }

}
