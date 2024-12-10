package com.amod.jpa.Repository;

import com.amod.jpa.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.password = :password WHERE u.id = :id")
    int updateUser(@Param("id") int id, @Param("name") String name, @Param("password") String password);
}
