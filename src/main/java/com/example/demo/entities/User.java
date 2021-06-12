package com.example.demo.entities;

import com.example.demo.enums.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

//Lombok

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;

    private Boolean locked = false;
    private Boolean enable = false;
    @Enumerated(EnumType.STRING)
    private Role role;


    public User() {
    }

    public User(String name, String username, String password, String email, Role role) {

        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;

    }

    // <editor-fold defaultstate="collapsed" desc=" INTERFACE METHODS ">
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Stores a String representation of an authority granted to the Authentication object
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        //Return singleton Set
        return Collections.singleton(authority);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" GETTERS & SETTERS ">

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long userId) {
        this.id = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
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

    public void setPassword(String psw) {
        this.password = psw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role rol) {
        this.role = rol;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


    // </editor-fold>
}