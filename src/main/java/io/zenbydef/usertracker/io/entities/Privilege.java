package io.zenbydef.usertracker.io.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "privileges")
public class Privilege implements GrantedAuthority {
    @Id
    @Column(name = "privilege_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String nameOfPrivilege;

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
    private List<RoleEntity> roles;

    public Privilege() {
    }

    public Privilege(String nameOfPrivilege) {
        this.nameOfPrivilege = nameOfPrivilege;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfPrivilege() {
        return nameOfPrivilege;
    }

    public void setNameOfPrivilege(String nameOfPrivilege) {
        this.nameOfPrivilege = nameOfPrivilege;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public String getAuthority() {
        return getNameOfPrivilege();
    }
}
