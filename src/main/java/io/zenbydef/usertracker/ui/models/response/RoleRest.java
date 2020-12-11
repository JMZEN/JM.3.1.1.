package io.zenbydef.usertracker.ui.models.response;

import io.zenbydef.usertracker.io.entities.Privilege;
import io.zenbydef.usertracker.io.shared.UserDto;

import java.util.List;
import java.util.Set;

public class RoleRest {
    private Long id;
    private String nameOfRole;
    private Set<UserRest> users;
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

    public Set<UserRest> getUsers() {
        return users;
    }

    public void setUsers(Set<UserRest> users) {
        this.users = users;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
