package io.zenbydef.usertracker.ui.models.response;

import java.io.Serializable;

public class RoleRest implements Serializable {

    private String nameOfRole;

    public String getNameOfRole() {
        return nameOfRole;
    }

    public void setNameOfRole(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }
}
