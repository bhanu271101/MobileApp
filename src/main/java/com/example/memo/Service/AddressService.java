package com.example.memo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.memo.DTO.AddressDTO;
import com.example.memo.Entity.AddressEntity;
import com.example.memo.Exception.SessionTimeoutException;
import com.example.memo.Mapper.AddressMapper;
import com.example.memo.Repository.AddressRepository;


@Service
public class AddressService {

    AddressMapper addressMapper=new AddressMapper();

    @Autowired
    private AddressRepository addressRepository;

    public AddressDTO addNewAddress(AddressDTO addressDTO,String userId)
    {
        AddressEntity addressEntity=addressMapper.toEntity(addressDTO);
        addressEntity.setUserId(userId);
        addressEntity.setDefault(true);
        addressRepository.save(addressEntity);
        return addressDTO;

    }

    public List<AddressDTO> getAllAddresses(String userId)
    {
        List<AddressEntity> addressEntities=addressRepository.findAllByUserId(userId);

        List<AddressDTO> addressDTOs=new ArrayList<>();

        ListIterator<AddressEntity> iterator=addressEntities.listIterator();

        while(iterator.hasNext())
        {
            AddressDTO addressDTO=addressMapper.toDTO(iterator.next());
            addressDTOs.add(addressDTO);
        }
        return addressDTOs;

    }

    public AddressDTO getDefaultAddress(String userId)
    {
        
        AddressEntity addressEntity=addressRepository.findByUserIdAndIsDefaultTrue(userId);
        return addressMapper.toDTO(addressEntity);
    }


    public String setDefaultAddress(String userId,long addressId)
    {

        if(userId==null)
        {
            throw new SessionTimeoutException("User session expired login to continue");
        }
        List<AddressEntity> addressEntities=addressRepository.findAllByUserId(userId);

        ListIterator<AddressEntity> iterator=addressEntities.listIterator();

        List<AddressEntity> addressEntities2=new ArrayList<>();
        while(iterator.hasNext())
        {
            AddressEntity addressEntity=iterator.next();
            addressEntity.setDefault(false);
            addressEntities2.add(addressEntity);
        }
        addressRepository.saveAll(addressEntities2);
        AddressEntity addressEntity=addressRepository.findByAddressId(addressId);
        addressEntity.setDefault(true);
        addressRepository.save(addressEntity);
        return "Success";
    }

    @RabbitListener(queues = "address.queue")
    public AddressDTO handleAddressRequest(String userId)
    {
        AddressDTO addressDTO=getDefaultAddress(userId);
        
        return addressDTO;
    }
}
