package com.mountblue.Blogproject.repository;

import com.mountblue.Blogproject.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken , String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
