package com.lww.design.graduation.service.address;

import com.lww.design.graduation.entity.po.Address;

import java.util.List;

public interface AddressService {
    Integer upsetAddress(Address address);

    List<Address> getAddressByUserId(Long userId);

    Integer deleteAddress(Long addressId);

    Integer modifyAddress(Address address, Long id);
}
