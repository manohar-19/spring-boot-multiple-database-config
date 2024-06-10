package com.manu.multiple_database.all_database.second_database.second_repository;

import com.manu.multiple_database.all_database.second_database.second_model.SecondUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondUserRepo extends JpaRepository<SecondUser,Integer> {
}
