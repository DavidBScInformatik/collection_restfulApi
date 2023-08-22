package de.davidjahnbscinformatik.ITSonixExample.controller;

import de.davidjahnbscinformatik.ITSonixExample.exception.ResourceNotFoundException;
import de.davidjahnbscinformatik.ITSonixExample.model.User;
import de.davidjahnbscinformatik.ITSonixExample.repository.CollectionRepository;
import de.davidjahnbscinformatik.ITSonixExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/collection")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = new ArrayList<>();
        this.userRepository.findAll().forEach(userList::add);
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No user with id = " + id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User _user = userRepository.save(new User(user.getUsername(), user.getPassword()));
    }

}
