package com.amod.jpa.Repository;

import com.amod.jpa.Model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepo extends JpaRepository<Tasks,Integer> {
}
