package com.dembasiby.usermanagement.dtos;

import com.dembasiby.usermanagement.enums.AddressType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private String street;
    private String city;
    private String country;
    private AddressType type;
    private long userId;
}
