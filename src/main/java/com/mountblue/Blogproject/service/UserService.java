package com.mountblue.Blogproject.service;

import com.mountblue.Blogproject.entity.UserData;
import com.mountblue.Blogproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@Component
public class UserService {

    @Autowired
    private UserRepository userrepo;

    public List<UserData> listAll() {
        return userrepo.findAll();
    }

    public void saveUsers(UserData user) {
        userrepo.save(user);
    }

    public UserData findById(Long id) {
        UserData user = userrepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("invalid id: " + id));
        return user;
    }


    public UserData get(Long id) {
        return userrepo.findById(id).get();
    }

    public void delete(Long id) {
        userrepo.deleteById(id);
    }

    public UserData findUser(String name) {
    return     userrepo.findByUsername(name);
    }
}
