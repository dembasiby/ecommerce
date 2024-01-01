package com.dembasiby.usermanagement.services.address;

import com.dembasiby.usermanagement.dtos.AddressDTO;
import com.dembasiby.usermanagement.entities.Address;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAllAddresses(long userId);
    AddressDTO getAddress(long userId, long addressId);
    AddressDTO updateAddress(long userId, long addressId, AddressDTO addressDTO);
    void deleteAddress(long userId, long addressId);
    Address createAddress(long userId, AddressDTO addressDTO);
}
