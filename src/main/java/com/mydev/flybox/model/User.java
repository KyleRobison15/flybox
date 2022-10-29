package com.mydev.flybox.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
@EnableMongoAuditing(dateTimeProviderRef = "yyyy-MM-dd")
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean active;

    private String role;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    private Address address;

    private List<Fly> flies;

    public User(String username, String password, String firstName, String lastName, String email, Boolean active, String role, LocalDate createDate, Address address, List<Fly> flies) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.role = role;
        this.createDate = createDate;
        this.address = address;
        this.flies = flies;
    }
}
