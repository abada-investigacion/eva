/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.rest.identity;

/*
 * #%L
 * Eva
 * %%
 * Copyright (C) 2013 Abada Servicios Desarrollo (investigacion@abadasoft.com)
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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
