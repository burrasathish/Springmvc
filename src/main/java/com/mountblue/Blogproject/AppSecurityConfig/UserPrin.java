package com.mountblue.Blogproject.AppSecurityConfig;

import com.mountblue.Blogproject.entity.Role;
import com.mountblue.Blogproject.entity.UserData;
import com.mountblue.Blogproject.repository.RoleRepository;
import com.mountblue.Blogproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class UserPrin implements UserDetails {



    private UserData user;

    public UserPrin(UserData user) {
        super();
        this.user = user;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {




//        List<Role> listUser = roleService.listAll();
//        Collection<GrantedAuthority> authorystem.out.println(role+"jkjkjkj");ities= AuthorityUtils.createAuthorityList();
//
//        for (Role role : listUser) {
//
//     System.out.println(role);
//
//            }
//
//        return authorities;
//
//
//

        List<GrantedAuthority> authorities=new ArrayList<>();
        System.out.println(user.getRole().getName());
                authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
            return authorities;

//
//        String role=user.getRole().getName();
//        System.out.println(role+"jkjkjkj");
////        Collection<GrantedAuthority> authorities= AuthorityUtils.createAuthorityList(role);
//        return Collections.singleton(new SimpleGrantedAuthority(role));

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
