package com.onix.msoauth.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name="security_role")
public class Role implements GrantedAuthority {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="role_name")
    private String roleName;

    @Column(name="description")
    private String description;

    public Role() {
    }

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }

    public Integer getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
