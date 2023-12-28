package com.dembasiby.usermanagement.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class AppUser extends BaseModel {
    private String username;
    private String password;
    private String email;

    @ManyToMany
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appUser")
    private Set<Address> addresses;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private UserProfile profile;
}
