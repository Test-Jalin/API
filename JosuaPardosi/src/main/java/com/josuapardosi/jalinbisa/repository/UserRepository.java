package com.josuapardosi.jalinbisa.repository;

import com.josuapardosi.jalinbisa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);

    @Modifying
    @Transactional
    @Query(value = "Delete from users  where name= ?1", nativeQuery = true)
    void deleteUser(String name);

}
