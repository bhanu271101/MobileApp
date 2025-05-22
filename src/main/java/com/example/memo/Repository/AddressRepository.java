package com.example.memo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.memo.Entity.AddressEntity;
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long>
{
    List<AddressEntity> findAllByUserId(String userId);

    AddressEntity findByUserIdAndIsDefaultTrue(String userId);

    AddressEntity findByAddressId(Long addressId);
}
