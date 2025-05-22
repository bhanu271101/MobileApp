package com.example.memo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.memo.Authentication.JWTToken;
import com.example.memo.DTO.AddressDTO;
import com.example.memo.Exception.SessionTimeoutException;
import com.example.memo.Service.AddressService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AddressController {


    @Autowired
    private AddressService addressService;

    @Autowired
    private JWTToken jwtToken;


    @PostMapping("/addNewAddress")
    public AddressDTO addNewAddress(HttpServletRequest httpServletRequest,@RequestBody AddressDTO addressDTO)
    {
        String userId=(String)httpServletRequest.getAttribute("userId");
        
        return addressService.addNewAddress(addressDTO,userId);
    }

    @GetMapping("/getAllAddresses")
    public List<AddressDTO> getAllAddresses(HttpServletRequest httpServletRequest)
    {
        String userId=(String)httpServletRequest.getAttribute("userId");
        return addressService.getAllAddresses(userId);
    }

    @GetMapping("/getDefaultAddress")
    public AddressDTO getDefaultAddress(HttpServletRequest httpServletRequest)
    {
        String userId=(String)httpServletRequest.getAttribute("userId");
        if(userId==null)
        {
            throw new SessionTimeoutException("User session expired login to continue");
        }
        return addressService.getDefaultAddress(userId);
    }


    @PostMapping("/setDefaultAddress")
    public String setDefaultAddress(HttpServletRequest httpServletRequest,@RequestParam long addressId)
    {
        String userId=(String)httpServletRequest.getAttribute("userId");

        return addressService.setDefaultAddress(userId, addressId);
    }

    @GetMapping("/getDefaultAddressForOrder/{token}")
    public AddressDTO getDefaultAddressForOrder(@PathVariable String token)
    {
        String userId=jwtToken.extractUserId(token);
        return addressService.getDefaultAddress(userId);
    }

}
