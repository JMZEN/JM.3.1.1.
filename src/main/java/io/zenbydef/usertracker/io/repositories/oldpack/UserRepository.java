package io.zenbydef.usertracker.io.repositories.oldpack;


import io.zenbydef.usertracker.io.entities.UserEntity;
import io.zenbydef.usertracker.io.entities.oldpack.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    User findUserById(Long id);
}
