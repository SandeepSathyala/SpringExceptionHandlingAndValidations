package com.example.springexceptionhandlingandvalidations.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private long id;

    @NotNull
    @Size(min = 4, max = 30)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp="(^$|[0-9]{10})")
    private long phoneNumber;
}
