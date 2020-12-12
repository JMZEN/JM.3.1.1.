//package io.zenbydef.usertracker.io.entities.oldpack;
//
//import io.zenbydef.usertracker.io.entities.Privilege;
//import io.zenbydef.usertracker.io.entities.UserEntity;
//
//import javax.persistence.*;
//import java.util.Collection;
//import java.util.Objects;
//import java.util.Set;
//
////@Entity
////@Table(name = "roles")
//public class Role {
//    @Id
//    @Column(name = "role_id")
//    private Long id;
//
//    @Column(name = "name")
//    private String nameOfRole;
//
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private Set<UserEntity> users;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "roles_privileges",
//            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "privilege_id"))
//    private Collection<Privilege> privileges;
//
//    public Role() {
//    }
//
//    public Role(Long id, String nameOfRole) {
//        this.id = id;
//        this.nameOfRole = nameOfRole;
//    }
//
//    public Role(String nameOfRole) {
//        this.nameOfRole = nameOfRole;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getNameOfRole() {
//        return nameOfRole;
//    }
//
//    public void setNameOfRole(String nameOfRole) {
//        this.nameOfRole = nameOfRole;
//    }
//
//    public Set<UserEntity> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<UserEntity> users) {
//        this.users = users;
//    }
//
//    public Collection<Privilege> getPrivileges() {
//        return privileges;
//    }
//
//    public void setPrivileges(Collection<Privilege> privileges) {
//        this.privileges = privileges;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Role role = (Role) o;
//        return Objects.equals(id, role.id) &&
//                Objects.equals(nameOfRole, role.nameOfRole) &&
//                Objects.equals(users, role.users) &&
//                Objects.equals(privileges, role.privileges);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (nameOfRole != null ? nameOfRole.hashCode() : 0);
//        result = 31 * result + (users != null ? users.hashCode() : 0);
//        result = 31 * result + (privileges != null ? privileges.hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return this.nameOfRole;
//    }
//}
