package org.contextmapper.generated.usermanagementcontext.web.rest;

import org.contextmapper.generated.usermanagementcontext.security.jwt.TokenProvider;
import org.contextmapper.generated.usermanagementcontext.service.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticateResource {

    public static class LoginDto {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setMail(String mail) {
            this.username = mail;
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

    private final CustomUserDetailsService userDetailsService;

    public AuthenticateResource(
        AuthenticationManager authenticationManager,
        TokenProvider tokenManager,
        CustomUserDetailsService userDetailsService
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponseModel> authenticateUser(@RequestBody LoginDto loginDto) throws Exception {
        try {
            final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );

            final String jwtToken = tokenManager.createToken(authentication, true);
            return ResponseEntity.ok(new JwtResponseModel(jwtToken));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
