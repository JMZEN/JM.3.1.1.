package io.zenbydef.usertracker.io.repositories;

import io.zenbydef.usertracker.io.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDtoRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserByEmail(String username);

    UserEntity findUserByUserId(String userId);
}
