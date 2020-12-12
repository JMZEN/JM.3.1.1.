package io.zenbydef.usertracker.io.shared;

import io.zenbydef.usertracker.io.entities.Privilege;

import java.io.Serializable;
import java.util.List;

public class RoleDto implements Serializable {
    private static final long serialVersionUID = 88005553555L;
    private Long id;
    private String nameOfRole;
    private List<UserDto> users;
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

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
