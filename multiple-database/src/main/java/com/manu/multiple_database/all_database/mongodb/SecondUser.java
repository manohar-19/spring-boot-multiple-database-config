package com.manu.multiple_database.all_database.mongodb;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "second_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecondUser {
    Integer _id;
    String name;
}
