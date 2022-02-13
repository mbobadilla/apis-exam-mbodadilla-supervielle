package com.supervielle.examen.authentication;

import com.supervielle.examen.exception.BadCredentialsException;
import com.supervielle.examen.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialsProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails u = userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, u.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    u.getAuthorities());
        } else {
            throw new BadCredentialsException
                    (ErrorCode.BadCredentials);
        }

    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType
                .equals(UsernamePasswordAuthenticationToken.class);
    }
}
