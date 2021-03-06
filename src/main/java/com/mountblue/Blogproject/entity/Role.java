package com.mountblue.Blogproject.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name="roles")
public class Role {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,unique = true)
    @NotEmpty
    private String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

//    public List<UserData> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<UserData> users) {
//        this.users = users;
//    }
}
