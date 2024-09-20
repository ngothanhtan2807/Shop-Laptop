package com.devpro.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_users")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username", length = 45, nullable = false)
    private String username;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "phone", length = 45, nullable = false)
    private String phone;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "gender", nullable = false)
    private boolean gender;

    @Column(name = "avatar", length = 100, nullable = false)
    private String avatar;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
