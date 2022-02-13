package com.supervielle.examen.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserInterceptor extends OncePerRequestFilter {

    @Autowired
    private UserDetailsConfig userDetailsConfig;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String reqUrl=request.getRequestURL().toString();

        if (!reqUrl.contains("swagger") && !reqUrl.contains("api-docs") && !reqUrl.contains("h2-console")) {
            String user = request.getHeader("user");
            String password = request.getHeader("password");
            UserDetails userDetails = userDetailsConfig.loadUserByUsername(user + "-" + password);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(request);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
