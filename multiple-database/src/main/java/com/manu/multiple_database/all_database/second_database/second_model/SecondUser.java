package com.manu.multiple_database.all_database.second_database.second_model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "second_user")
public class SecondUser {
    @Id
    Integer id;
    String name;
}
