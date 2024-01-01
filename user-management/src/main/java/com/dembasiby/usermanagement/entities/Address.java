package com.dembasiby.usermanagement.entities;

import com.dembasiby.usermanagement.dtos.AddressDTO;
import com.dembasiby.usermanagement.enums.AddressType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseModel {
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    private String country;
    private AddressType type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;


    public static AddressDTO from(Address address) {
        AddressDTO addressDTO =  new AddressDTO();

        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setType(address.getType());
        addressDTO.setUserId(address.appUser.getId());

        return addressDTO;
    }

}