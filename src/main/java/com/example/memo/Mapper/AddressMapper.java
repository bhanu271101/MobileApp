package com.example.memo.Mapper;

import com.example.memo.DTO.AddressDTO;
import com.example.memo.Entity.AddressEntity;

public class AddressMapper {

      public AddressDTO toDTO(AddressEntity entity) {
        if (entity == null) {
            return null;
        }

        AddressDTO dto = new AddressDTO();
        dto.setAddressId(entity.getAddressId());
        dto.setHouseNumber(entity.getHouseNumber());
        dto.setStreetName(entity.getStreetName());
        dto.setCity(entity.getCity());
        dto.setDistrict(entity.getDistrict());
        dto.setState(entity.getState());
        dto.setPincode(entity.getPincode());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setUserName(entity.getUserName());
        return dto;
    }

    public AddressEntity toEntity(AddressDTO dto) {
        if (dto == null) {
            return null;
        }

        AddressEntity entity = new AddressEntity();
        entity.setAddressId(dto.getAddressId());
        entity.setHouseNumber(dto.getHouseNumber());
        entity.setStreetName(dto.getStreetName());
        entity.setCity(dto.getCity());
        entity.setDistrict(dto.getDistrict());
        entity.setState(dto.getState());
        entity.setPincode(dto.getPincode());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setUserName(dto.getUserName());
        
        return entity;
    }

}
