package com.EstoqueFacil.EstoqueFacil.utils;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {


    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    public boolean isLogado() {
        Authentication auth = getAuth();
        return auth != null &&
                auth.isAuthenticated() &&
                !auth.getPrincipal().equals("anonymousUser");
    }


    public boolean hasRole(String role) {
        Authentication auth = getAuth();

        if (auth == null) return false;

        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(role));
    }
}
