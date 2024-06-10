package com.manu.multiple_database.all_database.first_database.first_repository;

import com.manu.multiple_database.all_database.first_database.first_model.FirstUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstUserRepo extends JpaRepository<FirstUser,Integer> {
}
