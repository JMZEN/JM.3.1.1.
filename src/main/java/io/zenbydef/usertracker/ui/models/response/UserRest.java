package io.zenbydef.usertracker.ui.models.response;

import java.io.Serializable;
import java.util.List;

public class UserRest implements Serializable {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private int age;
    private List<RoleRest> roles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<RoleRest> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleRest> roles) {
        this.roles = roles;
    }
}
