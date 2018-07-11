package ru.tsystem.javaschool.ordinaalena.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;
@Service
public class SecurityServiceImpl  implements SecurityService {
   @Autowired
   private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    private static final Logger logger = Logger.getLogger(SecurityServiceImpl.class);
    /**
     * Finding logged user.
     * @return User's email
     */
    @Override
    public String findLoggedInEmail() {

        logger.info("find logged user email");

        Object userDetails = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if(userDetails instanceof UserDetails)
            return ((UserDetails) userDetails).getUsername();

        return null;
    }

    /**
     * Autologin.
     * @param email Email
     * @param parole Parole
     */
    @Override
    public void autoLogin(String email, String parole) {

        logger.info("email: " + email.toString());

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, parole, userDetails.getAuthorities());

       authenticationManager.authenticate(authenticationToken);

        if(authenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
}
