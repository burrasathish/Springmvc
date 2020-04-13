package com.mountblue.Blogproject.repository;

import com.mountblue.Blogproject.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
          UserData findByUsername(String username);
    UserData findByEmailIgnoreCase(String email);

}
