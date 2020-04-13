package com.mountblue.Blogproject.AppSecurityConfig;

import com.mountblue.Blogproject.entity.UserData;
import com.mountblue.Blogproject.repository.UserRepository;
import com.mountblue.Blogproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class myUser implements UserDetailsService {

    @Autowired
    private UserRepository repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserData user=repo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User 404");
        }
      return new UserPrin(user);
    }
}
