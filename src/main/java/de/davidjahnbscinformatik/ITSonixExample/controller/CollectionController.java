package de.davidjahnbscinformatik.ITSonixExample.controller;

import de.davidjahnbscinformatik.ITSonixExample.exception.ResourceNotFoundException;
import de.davidjahnbscinformatik.ITSonixExample.model.Collection;
import de.davidjahnbscinformatik.ITSonixExample.model.User;
import de.davidjahnbscinformatik.ITSonixExample.repository.CollectionRepository;
import de.davidjahnbscinformatik.ITSonixExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CollectionRepository collectionRepository;

    @GetMapping("/view/{id}")
    public ResponseEntity<Collection> getViewById(@PathVariable(value = "id") int id){
        Collection collection = this.collectionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Collection"));
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @PostMapping("create/{userId}")
    public ResponseEntity<Collection> createCollection(@PathVariable(value = "userId") int userId, @RequestBody Collection collection){

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user with id = " + userId));;
        collection.setUser(user);
        collection.setCreatedOn(new Date());
        Collection newCollection = this.collectionRepository.save(collection);

        return new ResponseEntity<>(newCollection, HttpStatus.OK);
    }
}
