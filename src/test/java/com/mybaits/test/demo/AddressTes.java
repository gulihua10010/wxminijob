package com.mybaits.test.demo;

import com.mybaits.test.demo.bean.Address;
import com.mybaits.test.demo.dao.AddressDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressTes {
    @Autowired
    AddressDao dao;

    @Test
    public void tt(){
        Address address=new Address("ddd11","dddd",1);
//        dao.insertAddress(address);
//        address.setId(1);
//        dao.updateAddress(address);
         List<Address > addresses=dao.getAddressByUid(10);
        System.out.println(addresses);
    }
}
