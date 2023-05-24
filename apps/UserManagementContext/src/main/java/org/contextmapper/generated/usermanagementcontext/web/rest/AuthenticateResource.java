package org.contextmapper.generated.usermanagementcontext.web.rest;

import org.contextmapper.generated.usermanagementcontext.domain.enumeration.UserStatus;
import org.contextmapper.generated.usermanagementcontext.repository.CustomUserInfosRepository;
import org.contextmapper.generated.usermanagementcontext.security.jwt.TokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class AuthenticateResource {

    public static class LoginDto {
        private String mail;
        private String password;

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class JwtResponseModel {
        private final String token;

        public JwtResponseModel(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenManager;

    private final CustomUserInfosRepository userInfosRepo;

    public AuthenticateResource(
        AuthenticationManager authenticationManager,
        TokenProvider tokenManager,
        CustomUserInfosRepository userInfosRepo
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.userInfosRepo = userInfosRepo;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponseModel> authenticateUser(@RequestBody LoginDto loginDto) throws Exception {
        try {
            final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getMail(), loginDto.getPassword())
            );

            userInfosRepo.findByMail(loginDto.getMail()).ifPresent(userInfos -> {
                if (userInfos.getStatus() == UserStatus.WAITING_VALIDATION) {
                    throw new RuntimeException("User is not yet validated " + loginDto.getMail());
                }
            });

            final String jwtToken = tokenManager.createToken(authentication, true);
            return ResponseEntity.ok(new JwtResponseModel(jwtToken));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Principal> currentUser(Principal principal) throws Exception {
        return ResponseEntity.ok(principal);
    }
}
