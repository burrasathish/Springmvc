package com.mountblue.Blogproject.entity;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
@Entity(name = "UserData")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
//    @NotBlank
//    @Size(min = 2, max = 18, message = "Name must be between 2 and 18")
    @Column(nullable=false)
    @NotEmpty()
    private String username;
//    @Valid
//    @NotBlank
    @Email(message ="{errors.invalid_email}")
    private String email;
//
//    @NotBlank
//    @Size(min =8 , max = 18, message = "Name must be between 2 and 18")
    @Column(nullable=false)
    @Size(min=4)
    private String password;
    @ManyToOne(cascade = CascadeType.PERSIST)
   private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private boolean IsEnabled;

    public boolean isEnabled() {
        return IsEnabled;
    }

    public void setEnabled(boolean enabled) {
        IsEnabled = enabled;
    }
//
//    @ManyToOne
//    private Post post;

    public UserData() {

    }

//
//    public Post getPost() {
//        return post;
//    }
//
//    public void setPost(Post post) {
//        this.post = post;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
