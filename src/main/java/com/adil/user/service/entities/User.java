package com.adil.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="micro_users")
public class User {
    @Id
    @Column(name = "ID")
    private String userId;
    @Column(name = "NAME", length = 20)
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ABOUT")
    private String about;


    //Transient means dont store this feild in DB
    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
