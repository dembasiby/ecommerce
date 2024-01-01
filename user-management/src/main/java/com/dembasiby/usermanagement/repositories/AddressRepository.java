package com.dembasiby.usermanagement.repositories;

import com.dembasiby.usermanagement.entities.Address;
import com.dembasiby.usermanagement.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByAppUser_Id(long userId);
    Optional<Address> findAddressByAppUserAndId(AppUser user, long addressId);
}
