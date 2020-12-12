package io.zenbydef.usertracker.io.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "roles")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    private String nameOfRole;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Privilege> privileges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfRole() {
        return nameOfRole;
    }

    public void setNameOfRole(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
