package de.davidjahnbscinformatik.ITSonixExample.controller;

import de.davidjahnbscinformatik.ITSonixExample.model.User;
import de.davidjahnbscinformatik.ITSonixExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/handle")
    public ResponseEntity<User> handleLogin(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){

        User user = this.userRepository.findUserByUsername(username);

        if(user != null){
            if(this.passwordEncoder.matches(password, user.getPassword())){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }else{
                return new ResponseEntity("Password not correct", HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity("User not found", HttpStatus.BAD_REQUEST);
        }
    }
}
