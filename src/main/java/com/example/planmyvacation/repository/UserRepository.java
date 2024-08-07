package com.example.planmyvacation.repository;

import com.example.planmyvacation.model.entity.Role;
import com.example.planmyvacation.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);

    @Modifying(clearAutomatically = true)
    @Query( value = """
                       UPDATE users
                       SET username = :username, email = :email, role_id = (SELECT id FROM roles WHERE role = :role)
                       WHERE id = :id
                       """,
            nativeQuery = true )
    void updateUserById(
            @Param("id") Long id,
            @Param("username") String username,
            @Param("email") String email,
            @Param("role") String role
    );

}
