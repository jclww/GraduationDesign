package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.Address;

import java.util.List;

public interface AddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Long id);
    List<Address> selectByUserId(Long userId);
    List<Address> search(Address address);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
    int updateDefaultByUserId(Address record);
}