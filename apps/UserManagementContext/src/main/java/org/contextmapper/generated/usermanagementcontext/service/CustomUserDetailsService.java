package org.contextmapper.generated.usermanagementcontext.service;

import org.contextmapper.generated.usermanagementcontext.domain.UserInfos;

import org.contextmapper.generated.usermanagementcontext.domain.enumeration.Roles;
import org.contextmapper.generated.usermanagementcontext.domain.enumeration.UserStatus;
import org.contextmapper.generated.usermanagementcontext.repository.CustomUserInfosRepository;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserInfosRepository userInfosRepository;

    public CustomUserDetailsService(CustomUserInfosRepository userInfosRepository,
                                    PasswordEncoder passwordEncoder) {
        this.userInfosRepository = userInfosRepository;
        saveAdmin("admin@admin.fr", passwordEncoder.encode("admin"));
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws RuntimeException {
        UserInfos user = userInfosRepository.findByMail(mail)
            .orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email: "+ mail));

        if (user.getStatus() == UserStatus.WAITING_VALIDATION) {
            throw new DisabledException("User is not yet validated " + mail);
        }

        if (user.getStatus() == UserStatus.REJECTED) {
            throw new DisabledException("User is rejected " + mail);
        }

        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(user
            .getRole().toString())
        );

        return new org.springframework.security.core.userdetails.User(user.getMail(),
            user.getPassword(),
            authorities);
    }

    public void saveAdmin(String email, String password) {
        return;
        /*if (userInfosRepository.findByMail(email).isPresent()) {
            return;
        }

        UserInfos user = new UserInfos();
        user.setMail(email);
        user.setFirstname("Admin");
        user.setLastname("User");
        user.setPassword(password);
        user.setRole(Roles.EVALUATOR);
        user.setStatus(UserStatus.VALIDATED);
        userInfosRepository.save(user);*/
    }
}
