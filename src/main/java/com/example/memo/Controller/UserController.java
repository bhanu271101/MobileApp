package com.example.memo.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.memo.Authentication.JWTToken;
import com.example.memo.DTO.ChangePasswordDto;
import com.example.memo.DTO.LoginDTO;
import com.example.memo.DTO.RequestResetPasswordDTO;
import com.example.memo.DTO.ResetPasswordDTO;
import com.example.memo.DTO.UserDto;
import com.example.memo.DTO.UserRegisterationDto;
import com.example.memo.Entity.User;
import com.example.memo.Repository.UserRepository;
import com.example.memo.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;


@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTToken jwtToken;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO)
    {           

       String login= userService.userLogin(loginDTO);
       if(login.equals("Login successful"))
       {
            User user=userRepository.findByEmail(loginDTO.getEmail());
            String token=jwtToken.generateToken(user.getUserId());
            Map<String,String> response=new HashMap<>();
            response.put("token", token);
            response.put("userId", user.getUserId());
            return ResponseEntity.ok(response);
       }
       else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(login);
    }

    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegisterationDto userRegisterationDto)
    {
        UserDto dto=userService.registerService(userRegisterationDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }


    @GetMapping("/getUser")
    public ResponseEntity<UserDto> getUser(HttpServletRequest httpServletRequest)
    {
        String id=(String)httpServletRequest.getAttribute("userId");
        return new ResponseEntity<>(userService.getUser(id),HttpStatus.ACCEPTED);
    }
    

    @GetMapping("/validateUser")
    public ResponseEntity<?> validateRegistredUser(@RequestParam String token)
    {
        String message=userService.validateVerificationToken(token);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto) 
    {
       String mg=userService.passwordChangeService(changePasswordDto);
       return new ResponseEntity<>(mg,HttpStatus.ACCEPTED);
    }


    @PostMapping("/resetToken")
    public ResponseEntity<?> resetVerificationToken(@RequestParam String email) 
    {
        String message=userService.resetVerificationToken(email);
        return new ResponseEntity<>(message,HttpStatus.CREATED);

    }

    @GetMapping("/cronjob")
    public String dummyForCronjob()
    {
        return "Cronjob ran successfully";
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) 
    {
        String message=userService.resetPassword(resetPasswordDTO);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PostMapping("/requestResetPassword")
    public void RequestResetPassword(@RequestBody RequestResetPasswordDTO requestResetPasswordDTO)
    {
        userService.requestResetPassword(requestResetPasswordDTO);
    }

}
