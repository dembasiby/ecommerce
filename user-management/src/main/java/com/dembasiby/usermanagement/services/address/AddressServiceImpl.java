package com.dembasiby.usermanagement.services.address;

import com.dembasiby.usermanagement.dtos.AddressDTO;
import com.dembasiby.usermanagement.exceptions.AddressNotFoundException;
import com.dembasiby.usermanagement.exceptions.UserNotFoundException;
import com.dembasiby.usermanagement.entities.Address;
import com.dembasiby.usermanagement.entities.AppUser;
import com.dembasiby.usermanagement.repositories.AddressRepository;
import com.dembasiby.usermanagement.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AppUserRepository appUserRepository;

    public AddressServiceImpl(AddressRepository addressRepository, AppUserRepository appUserRepository) {
        this.addressRepository = addressRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<AddressDTO> getAllAddresses(long userId) {
        List<Address> addresses = addressRepository.findAllByAppUser_Id(userId);
        List<AddressDTO> addressDTOList = new ArrayList<>();

        for (Address address : addresses) {
            addressDTOList.add(Address.from(address));
        }

        return addressDTOList;
    }

    @Override
    public AddressDTO getAddress(long userId, long addressId) {
        return Address.from(findAddress(userId, addressId));
    }

    @Override
    public AddressDTO updateAddress(long userId, long addressId, AddressDTO addressDTO) {
        Address addressToBeUpdated  = findAddress(userId, addressId);
        addressToBeUpdated.setType(addressDTO.getType());
        addressToBeUpdated.setCity(addressDTO.getCity());
        addressToBeUpdated.setCountry(addressDTO.getCountry());
        addressToBeUpdated.setStreet(addressDTO.getStreet());

        addressRepository.save(addressToBeUpdated);

        return Address.from(addressToBeUpdated);
    }

    @Override
    public void deleteAddress(long userId, long addressId) {
        addressRepository.delete(findAddress(userId, addressId));
    }

    @Override
    public Address createAddress(long userId, AddressDTO addressDTO) {
        AppUser user = findUser(userId);
        if (user  ==  null) return null;

        Address newAddress = new Address();
        newAddress.setType(addressDTO.getType());
        newAddress.setStreet(addressDTO.getStreet());
        newAddress.setCity(addressDTO.getCity());
        newAddress.setCountry(addressDTO.getCountry());
        newAddress.setAppUser(user);
        addressRepository.save(newAddress);
        return newAddress;
    }

    private AppUser findUser(long userId) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(userId);
        return optionalAppUser.orElse(null);
    }

    private Address findAddress(long userId, long addressId)  {
        AppUser user =  findUser(userId);
        if (user ==  null) {
            throw new UserNotFoundException("User not found  with id " + userId);
        }
        return user.getAddresses().stream()
                .filter(address -> address.getId()  == addressId)
                .findFirst()
                .orElseThrow(() -> new AddressNotFoundException("Address not found or does not belong to user"));

    }
}
