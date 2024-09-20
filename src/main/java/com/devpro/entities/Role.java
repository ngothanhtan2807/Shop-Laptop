package com.devpro.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_roles")
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {
    private static final long serialVersionUID = -1246332751520787944L;

    @Column(name = "name", length = 45, nullable = false)
    private String name;
    @Column(name = "description", length = 45, nullable = false)
    private String description;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    @Override
    public String getAuthority() {
        return this.name;
    }
}
