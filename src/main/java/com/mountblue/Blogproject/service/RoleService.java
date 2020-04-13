package com.mountblue.Blogproject.service;


import com.mountblue.Blogproject.entity.Role;
import com.mountblue.Blogproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    public Role get(String name){
      return roleRepository.findByName(name);
    }


    public List<Role> listAll(){
        return roleRepository.findAll();
    }

    public Role  getName(String name){
        return roleRepository.findByName(name);
    }

    public Role getById(long id) {
        return roleRepository.findById(id).get();
    }

}
