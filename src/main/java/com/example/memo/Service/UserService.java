package com.example.memo.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.memo.DTO.ChangePasswordDto;
import com.example.memo.DTO.LoginDTO;
import com.example.memo.DTO.RequestResetPasswordDTO;
import com.example.memo.DTO.ResetPasswordDTO;
import com.example.memo.DTO.UserDto;
import com.example.memo.DTO.UserRegisterationDto;
import com.example.memo.Entity.User;
import com.example.memo.Entity.VerificationToken;
import com.example.memo.Repository.UserRepository;
import com.example.memo.Repository.VerificationTokenRepository;
import com.example.memo.Utility.PasswordValidation;
import com.example.memo.Utility.PhoneNumberValidation;
import com.example.memo.Utility.TokenExperationTime;

import jakarta.transaction.Transactional;

import com.example.memo.Exception.*;
import com.example.memo.Mapper.UserMapper;

@org.springframework.stereotype.Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

   

    @Autowired
    private EmailService mailService;

    UserMapper userMapper= new UserMapper();

    
    public String generateUserId()
    {
        Random random=new Random();
        long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    

    
    public String userLogin(LoginDTO loginDTO)
    {
        if(loginDTO!=null)
        {
            User user=userRepository.findByEmail(loginDTO.getEmail());
            if(user!=null && user.getPassword().equals(loginDTO.getPassword()))
            {
                 if(user.isEnabled()==true)
                 {                    
                     return "Login successful";
                 }
                 else{
                     return "please verify the user";
                 }
                
            }
            
           
        }
        return "Wrong username or password";
    }

 

    public UserDto registerService(UserRegisterationDto userRegisterationDto)
    {

        if(userRegisterationDto==null
        ||userRegisterationDto.getEmail()==null
        ||userRegisterationDto.getName()==null
        ||userRegisterationDto.getPassword()==null
        ||userRegisterationDto.getConfirmPassword()==null)throw new NullPointerException("User details should not be null");

        if(userRepository.findByEmail(userRegisterationDto.getEmail())!=null)
        throw new UserNameAlreadyExistsException("User already exists");

        if(!userRegisterationDto.getConfirmPassword().equals(userRegisterationDto.getPassword()))
        throw new PasswordNotMatchingException("Password and confirm password are not matching");

        if(!PasswordValidation.passwordValidation(userRegisterationDto.getPassword()))
        throw new PasswordValidationException("Password must contain 8 digits"+","+"special characters"+","+"numbers");
        
        if(!PhoneNumberValidation.isValidPhoneNumber(userRegisterationDto.getPhoneNumber()))
        throw new PhoneNumberValidationException("Invalid phone number");
        
        User user=userMapper.userRegisterationDtoToUser(userRegisterationDto);
        user.setUserId(generateUserId());
        user.setTokens(user.getTokens()+1);
        String token=UUID.randomUUID().toString();
        User savedUser= userRepository.save(user);
        saveVerificationToken(savedUser, token);

        String URL="https://mobileapp-4.onrender.com/user/validateUser?token="+token;

        mailService.sendEmail(savedUser.getEmail(),"User Verification","Please click on this URl to verfy "+ URL);
        return userMapper.userToUserDto(user);
        
    }

    @SuppressWarnings("deprecation")
    public UserDto getUser(String id)
    {
        User user=userRepository.findByUserId(id);
        UserDto userdto=userMapper.userToUserDto(user);
        return userdto;
    }

    @Transactional
    public String validateVerificationToken(String token)
    {
        
        VerificationToken verificationToken=verificationTokenRepository.findByToken(token);
        if(verificationToken==null)
        {
            return "Token not found";
        }
        else
        {

            User user=verificationToken.getUser();

            if(TokenExperationTime.calculateDifferenceInTime(verificationToken.getExpirationTime(),LocalDateTime.now())==1)
            {
                if(user.isEnabled()==true)
                {
                    return "User already verified";
                }
                user.setEnabled(true);
                userRepository.save(user);
                return "User verification successful";
            }
            else{
                verificationTokenRepository.deleteByToken(token);
                return "Token expired";
            }
        }
    }


    public void saveVerificationToken(User user, String token)
    {
        VerificationToken verificationToken=new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }

    public String passwordChangeService(ChangePasswordDto changePasswordDto) 
    {
        if(changePasswordDto.getEmail()==null
        ||changePasswordDto.newPassword==null
        ||changePasswordDto.getOldPassword()==null
        || changePasswordDto.getMatchingPassword()==null) throw new NullPointerException("Fields should not be null");

        if(userRepository.findByEmail(changePasswordDto.getEmail())==null)
        throw new UserNotFoundException("Email does not exists");

        if(!changePasswordDto.getNewPassword().equals(changePasswordDto.getMatchingPassword()))
        throw new PasswordNotMatchingException("New Password and confirm password are not matching");

        User user=userRepository.findByEmail(changePasswordDto.getEmail());

        if(!user.getPassword().equals(changePasswordDto.getOldPassword()))
        throw new PasswordNotMatchingException("Old password is not matching");

        user.setPassword(changePasswordDto.getNewPassword());
        userRepository.save(user);

        return "Password changed successfully";

    }


    public String resetVerificationToken(String email) 
    {
        User user=userRepository.findByEmail(email);
        if(user!=null)
        {
            
            String token= UUID.randomUUID().toString();
            saveVerificationToken(user, token);
            String URL="http://mobileapp-4.onrender.com/user/validateUser?token="+token;

            mailService.sendEmail(user.getEmail(),"User Registeration","Please click on this URl to verfy "+ URL);
            return "Reset verificatoin token sent successfully check you mail";
        }
        else
        {
            throw new UserNotFoundException("User does not exist");
        }
    }

    public String resetPassword(ResetPasswordDTO resetPasswordDTO)
    {
       
        if(
        resetPasswordDTO.getEmail()==null||
        resetPasswordDTO.getNewPassword()==null||
        resetPasswordDTO.getMatchingPassword()==null
        )
        throw new NullPointerException("user details should not be null");

        if(!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getMatchingPassword()))
        throw new PasswordNotMatchingException("both password and confirm password should be same");
        
        User user=userRepository.findByEmail(resetPasswordDTO.getEmail());
        user.setPassword(resetPasswordDTO.getNewPassword());
        userRepository.save(user);

        return "Password reset successful";

    }

    public void requestResetPassword(RequestResetPasswordDTO requestResetPasswordDTO)
    {
        if(requestResetPasswordDTO==null)throw new NullPointerException("Email should not be null");

        if(userRepository.findByEmail(requestResetPasswordDTO.getEmail())==null)
        throw new UserNotFoundException("Email does not exists");

    }

}
