package io.zenbydef.usertracker.security.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<GrantedAuthority> authorityList = new ArrayList<>(authorities);

        for (GrantedAuthority grantedAuthority : authorityList) {
            if (grantedAuthority.getAuthority().equals("user.list.read")) {
                httpServletResponse.sendRedirect("/admin");
                return;
            }
        }
        httpServletResponse.sendRedirect("/user");
    }
}
