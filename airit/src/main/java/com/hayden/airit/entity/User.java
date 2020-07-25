package com.hayden.airit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = -6061427855555246800L;
    private Integer id;
    private String phone;
    private String username;
    private String password;
    private String email;
    private Integer balance;
    private Manager manager;
    private Set<Logfile> logfiles;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @OneToMany
    @JsonIgnoreProperties("user")
    public Set<Logfile> getLogfiles() {
        return logfiles;
    }

    public void setLogfiles(Set<Logfile> logfiles) {
        this.logfiles = logfiles;
    }

    @ManyToOne
    @JoinColumn(name="userManager")
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }


}
