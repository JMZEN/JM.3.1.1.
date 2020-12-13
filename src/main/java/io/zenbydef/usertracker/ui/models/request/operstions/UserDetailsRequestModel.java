package io.zenbydef.usertracker.ui.models.request.operstions;

import java.util.List;

public class UserDetailsRequestModel {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private List<RoleRequestModel> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<RoleRequestModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleRequestModel> roles) {
        this.roles = roles;
    }
}
