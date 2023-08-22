package de.davidjahnbscinformatik.ITSonixExample.controller;

import de.davidjahnbscinformatik.ITSonixExample.model.Platform;
import de.davidjahnbscinformatik.ITSonixExample.model.User;
import de.davidjahnbscinformatik.ITSonixExample.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/collection")
public class PlatformController {

    @Autowired
    PlatformRepository platformRepository;

    @GetMapping("/platform")
    public ResponseEntity<List<Platform>> getAllPlatforms(){
        List<Platform> platformList = new ArrayList();
        this.platformRepository.findAll().forEach(platformList::add);
        if (platformList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(platformList, HttpStatus.OK);
        }
    }

    @PostMapping("/platform")
    public ResponseEntity<Platform> create(@RequestBody Platform newPlatform){
        this.platformRepository.save(newPlatform);
        return new ResponseEntity<Platform>(newPlatform, HttpStatus.OK);
    }
}
