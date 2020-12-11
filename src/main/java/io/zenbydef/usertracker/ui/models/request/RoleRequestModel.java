package io.zenbydef.usertracker.ui.models.request;

import io.zenbydef.usertracker.io.entities.Privilege;
import io.zenbydef.usertracker.ui.models.response.UserRest;

import java.util.List;
import java.util.Set;

public class RoleRequestModel {
    private String nameOfRole;
    private Set<UserDetailsRequestModel> users;
    private List<Privilege> privileges;

    public String getNameOfRole() {
        return nameOfRole;
    }

    public void setNameOfRole(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }

    public Set<UserDetailsRequestModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDetailsRequestModel> users) {
        this.users = users;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
