/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.rest.identity;

import java.security.Principal;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author katsu
 */
@Controller
public class IdentityController {

    /**
     * Returns all roles of logged users.
     *
     * @param request Do nothing.
     * @return Return all roles of logged users.
     */
    @RequestMapping(value = "/rs/identity/user/roles/list", method = RequestMethod.GET)
    public Collection<? extends GrantedAuthority> getRoles(HttpServletRequest request) {
        Principal p = request.getUserPrincipal();
        if (p instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken u = (UsernamePasswordAuthenticationToken) p;
            if (u.getPrincipal() instanceof UserDetails) {
                UserDetails user = (UserDetails) u.getPrincipal();                
                return user.getAuthorities();
            }
        }
        return null;
    }
}
