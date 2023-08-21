package com.example.JavaSQL.service;

import com.example.JavaSQL.entity.AddressEntity;
import com.example.JavaSQL.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.util.StringUtils;

@Service
@Slf4j
public class AddressService {
    @Autowired
    AddressRepository addressRepo;

    public AddressEntity update(long id, String address) throws Exception {
        Optional<AddressEntity> addressData = addressRepo.findById(id);
        if (addressData.isPresent()) {
            AddressEntity newAddressData = addressData.get();
            if (StringUtils.hasLength(address)) {
                newAddressData.setAddress(address);
                return newAddressData;
            }
            throw new Exception("Address info is required");
        }
        throw new Exception("Address not found");
    }
}
