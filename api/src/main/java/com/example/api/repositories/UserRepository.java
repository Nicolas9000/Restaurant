package com.example.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.api.models.Admin;
import com.example.api.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // ?1 correspond a name et ?2 correspond a email
    // on peut même faire ASC DESC ect
    // @Query("select u from User u where u.name like %?1 or u.name like %?2")
    // List<User> findByNameEmail(String name, String email);

    @Query("select u from User u where u.admin = ?1")
    List<User> findAdmin(Admin admin);

    @Query("select u from User u where u.name like %:name or u.email like :email")
    List<User> findByNameEmail(@Param("name") String name, @Param("email") String email);

    User findByEmail(String email);

}
