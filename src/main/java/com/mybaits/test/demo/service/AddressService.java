package com.mybaits.test.demo.service;

import com.mybaits.test.demo.bean.Address;

import java.util.List;

public interface AddressService {
    public Address getAddressById(Integer id);
    public List<Address> getAddressByUid(Integer uid);
    public  boolean insertAddress(Address address);
    public  boolean updateAddress(Address address);
    public  boolean deleteAddress(Address address);

}
