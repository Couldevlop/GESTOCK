package com.openlab.gestiondestock.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtilities jwtUtilities;
    private final CustomerUserDetailsService customerUserDetailsService;
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

         final String autHeader = request.getHeader("authorization");
         String idEntreprise = null;
        String jwt = null;
        String username = null;

         if(autHeader != null && autHeader.startsWith("Bearer")){
              jwt = autHeader.substring(7);
              username = jwtUtilities.extractUsername(jwt);
             idEntreprise = jwtUtilities.extractIdEntreprise(jwt);
         }


        //String token = jwtUtilities.getToken(request);
        if (username !=null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            jwtUtilities.validateToken(jwt);
            String email = jwtUtilities.extractUsername(jwt);

            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername() ,null , userDetails.getAuthorities());
                log.info("authenticated user with email :{}", email);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }
        MDC.put("idEntreprise", idEntreprise);
        filterChain.doFilter(request,response);
    }
}
