package com.lww.design.graduation.service.address.impl;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.po.Address;
import com.lww.design.graduation.mapper.AddressMapper;
import com.lww.design.graduation.service.address.AddressService;
import com.lww.design.graduation.utils.AppUtil;
import com.lww.design.graduation.utils.OrikaBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private OrikaBeanUtil orikaBeanUtil;

    @Override
    public Integer upsetAddress(Address address) {
        log.info("upsetAddress address:{}", JSON.toJSONString(address));
        if (AppUtil.nullOrDefault(address.getId())) {
            return addressMapper.insertSelective(address);
        } else {
            return addressMapper.updateByPrimaryKeySelective(address);
        }
    }
    @Override
    public List<Address> getAddressByUserId(Long userId) {
        log.info("getAddressByUserId userId:{}", userId);
        return addressMapper.selectByUserId(userId);
    }
    @Override
    public Integer deleteAddress(Long addressId) {
        log.info("deleteAddress addressId:{}", addressId);
        return addressMapper.deleteByPrimaryKey(addressId);
    }

    @Override
    public Integer modifyAddress(Address address, Long userId) {
        if (AppUtil.notNullOrDefault(address.getIsDefault())) {
            Address addressSo = new Address();
            addressSo.setUserId(userId);
            addressSo.setIsDefault(1);
            List<Address> addressList = addressMapper.search(addressSo);
            if (CollectionUtils.isNotEmpty(addressList)) {
                Address updateAddress = new Address();
                updateAddress.setUserId(userId);
                updateAddress.setIsDefault(0);
                addressMapper.updateDefaultByUserId(updateAddress);
            }
        }
        return addressMapper.updateByPrimaryKeySelective(address);
    }

}
