package com.hayden.airit.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "tb_manager")
public class Manager implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    @Column(nullable = false)
    private String username;

    private Set<User> users;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Column(nullable = false)
    private String password;



    @OneToMany
    @JsonIgnoreProperties("manager")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
