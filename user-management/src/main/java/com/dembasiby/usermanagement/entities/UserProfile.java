package com.dembasiby.usermanagement.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class UserProfile extends BaseModel {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

}
