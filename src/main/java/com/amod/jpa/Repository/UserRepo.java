package com.amod.jpa.Repository;

import com.amod.jpa.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.password = :password ,u.email=:email WHERE u.id = :id")
    int updateUser(@Param("id") int id, @Param("name") String name, @Param("password") String password,@Param("email") String email);


    //day-2 Custom Finder Methods in Spring Data JPA
    //1)contains keywords ....WHERE name LIKE %?%
    List<User>findByNameContaining(String keyword);

    //2)EndsWith keyword ....... WHERE name LIKE %?
    List<User>findByEmailEndsWith(String domain);

    //3)OrderedBy ... ORDER BY email ASC
    List<User>findByNameOrderByEmailAsc(String name);


}
