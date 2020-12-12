package io.zenbydef.usertracker.ui.models.response;

import io.zenbydef.usertracker.io.entities.Privilege;
import io.zenbydef.usertracker.io.shared.UserDto;

import java.util.List;
import java.util.Set;

public class RoleRest {

    private String nameOfRole;

    public String getNameOfRole() {
        return nameOfRole;
    }

    public void setNameOfRole(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }
}
