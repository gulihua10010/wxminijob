package com.mybaits.test.demo.service.impI;

import com.mybaits.test.demo.bean.Address;
import com.mybaits.test.demo.dao.AddressDao;
import com.mybaits.test.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AddressServiceImpI  implements AddressService {
    @Autowired
    AddressDao dao;
    @Override
    public Address getAddressById(Integer id) {
        return dao.getAddressById(id);
    }

    @Override
    public List<Address> getAddressByUid(Integer uid) {

        return dao.getAddressByUid(uid);
    }
    @Transactional
    @Override
    public boolean insertAddress(Address address) {
        if (address!=null&&address.getName()!=null){

            try {
                if (dao.insertAddress(address)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("添加地址失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("地址不能为空");
        }
    }
    @Transactional
    @Override
    public boolean updateAddress(Address address) {
        if (address!=null&&address.getId()!=null){

            try {
                if (dao.updateAddress(address)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("更新地址失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("地址不能为空");
        }
    }
    @Transactional
    @Override
    public boolean deleteAddress(Address address) {
        if (address!=null&&address.getId()!=null){

            try {
                if (dao.deleteAddress(address)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("删除地址失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("地址不能为空");
        }
    }
}
