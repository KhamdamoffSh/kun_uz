package com.example.util;

import com.example.Enum.ProfileRole;
import com.example.dto.ArticleDTO;
import com.example.dto.ArticleLikeDTO;
import com.example.dto.JwtDTO;
import com.example.entity.ArticleLikeEntity;
import com.example.exaption.AppMethodNotAllowedException;
import com.example.exaption.UnAuthorizedException;
import jakarta.servlet.http.HttpServletRequest;

public class SecurityUtil {
    public static JwtDTO getJwtDTO(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            String jwt = authToken.substring(7);
            return JwtUtil.decode(jwt);
        }
        throw new UnAuthorizedException("Not authorized");
    }

    public static JwtDTO hasRole(HttpServletRequest request, ArticleDTO article, String authToken, ProfileRole... requiredRoles) {
        JwtDTO jwtDTO = getJwtDTO(authToken);
        if (requiredRoles == null){
            return jwtDTO;
        }
        boolean found = false;
        for (ProfileRole role : requiredRoles) {
            if (jwtDTO.getRole().equals(role)) {
                found = true;
            }
        }
        if (!found) {
            throw new AppMethodNotAllowedException();
        }
        return jwtDTO;
    }

    public static JwtDTO hasRole(HttpServletRequest request, ProfileRole... requiredRoles) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        if (requiredRoles == null) {
            return new JwtDTO(id, role);
        }
        boolean found = false;
        for (ProfileRole required : requiredRoles) {
            if (role.equals(required)) {
                found = true;
            }
        }
        if (!found) {
            throw new AppMethodNotAllowedException();
        }
        return new JwtDTO(id, role);
    }
}
