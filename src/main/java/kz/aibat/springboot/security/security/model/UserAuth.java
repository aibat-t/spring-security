package kz.aibat.springboot.security.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="t_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email",length = 200)
    private String email;

    @Column(name="password",length = 200)
    private String password;

    @Column(name="full_name")
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<GrantAccess> grantAccessList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantAccessList;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
