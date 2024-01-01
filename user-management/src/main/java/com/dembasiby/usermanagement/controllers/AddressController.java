package com.dembasiby.usermanagement.controllers;

import com.dembasiby.usermanagement.dtos.AddressDTO;
import com.dembasiby.usermanagement.entities.Address;
import com.dembasiby.usermanagement.exceptions.AddressNotFoundException;
import com.dembasiby.usermanagement.exceptions.UserNotFoundException;
import com.dembasiby.usermanagement.services.address.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("{userId}/addresses")
    public ResponseEntity<AddressDTO> createAddress(@PathVariable("userId") long userId, @RequestBody AddressDTO addressDTO) {
        Address address =  addressService.createAddress(userId, addressDTO);

        if (address == null) {
            throw new UserNotFoundException("User not found.");
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(address.getId())
                .toUri();

        return ResponseEntity.created(location).body(Address.from(address));
    }

    @GetMapping("{userId}/addresses")
    public ResponseEntity<List<AddressDTO>> getAllAddresses(@PathVariable("userId") long userId) {
        List<AddressDTO> addressDTOS = addressService.getAllAddresses(userId);
        return ResponseEntity.ok(addressDTOS);
    }

    @GetMapping("{userId}/addresses/{addressId}")
    public ResponseEntity<?> getAddress(@PathVariable("userId") long userId,
                                                    @PathVariable("addressId") long addressId) {
        try {
            AddressDTO address = addressService.getAddress(userId, addressId);
            return ResponseEntity.ok(address);
        } catch (AddressNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }



    @PutMapping("{userId}/addresses/{addressId}")
    public ResponseEntity<?> updateAddress(@PathVariable("userId") long userId,
                                                    @PathVariable("addressId") long addressId,
                                                    @RequestBody AddressDTO addressDTO) {
        try {
            AddressDTO address = addressService.updateAddress(userId, addressId, addressDTO);
            return ResponseEntity.ok(address);
        } catch (AddressNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @DeleteMapping("{userId}/addresses/{addressId}")
    public ResponseEntity<Void> updateAddress(@PathVariable("userId") long userId,
                                                    @PathVariable("addressId") long addressId) {
        try {
            addressService.deleteAddress(userId, addressId);
            return ResponseEntity.noContent().build();
        } catch (AddressNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
