package com.sensorium.api.utils;


import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;
import com.sensorium.api.entity.enums.RoleEnum;;;


public class AuthorizationAccess {

    public static boolean hasRole(RoleEnum role){
        boolean has = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().filter(r -> {
            return r.getAuthority().toString().equals(role.toString());
        }).collect(Collectors.toList()).size() > 0;
        return has;
    }
}
