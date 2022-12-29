package com.example.intractivtest.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_table")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
    private Long id;
    private String name;
    private String phone;
    private String password;

}
