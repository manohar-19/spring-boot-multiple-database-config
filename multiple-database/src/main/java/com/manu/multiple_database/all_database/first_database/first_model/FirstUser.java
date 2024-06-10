package com.manu.multiple_database.all_database.first_database.first_model;

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
@Table(name = "first_user")
public class FirstUser {
    @Id
    Integer id;
    String name;
}
