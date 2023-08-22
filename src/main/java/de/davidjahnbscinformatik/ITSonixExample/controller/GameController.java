package de.davidjahnbscinformatik.ITSonixExample.controller;

import de.davidjahnbscinformatik.ITSonixExample.function.Cover;
import de.davidjahnbscinformatik.ITSonixExample.exception.ResourceNotFoundException;
import de.davidjahnbscinformatik.ITSonixExample.model.Collection;
import de.davidjahnbscinformatik.ITSonixExample.model.Game;
import de.davidjahnbscinformatik.ITSonixExample.repository.CollectionRepository;
import de.davidjahnbscinformatik.ITSonixExample.repository.GameRepository;
import de.davidjahnbscinformatik.ITSonixExample.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/collection")
public class GameController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private CollectionRepository collectionController;

    @GetMapping("/game")
    public ResponseEntity<Iterable<Game>> getAll(){
        return new ResponseEntity<Iterable<Game>>(this.gameRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable(value = "id") int gameId){
        Game game = this.gameRepository.findById(gameId).orElseThrow(() -> new ResourceNotFoundException("No game with id = " + gameId));
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PostMapping("/game/{id}")
    public ResponseEntity<Game> create(@PathVariable(value = "id") int collectionId, @Valid @RequestBody Game newGame){

        Collection collection = this.collectionController.findById(collectionId).orElseThrow(() -> new ResourceNotFoundException("No collection with id = " + collectionId));
        newGame.setCollection_Id(newGame.getCollection_Id());
        newGame.setImgPath(Cover.creatCoverPath(newGame.getProductId()));
        collection.getGamelist().add(newGame);
        this.gameRepository.save(newGame);
        return new ResponseEntity<Game>(newGame, HttpStatus.OK);
    }
    
    @PutMapping("/edit")
    public ResponseEntity<Game> editGame(@RequestBody Game editedGame){
        Optional<Game> dbGame = this.gameRepository.findById(editedGame.getId());

        if(dbGame.isPresent()){
            Game savedGame = this.gameRepository.save(editedGame);
            return new ResponseEntity<>(savedGame, HttpStatus.OK);
        }else{
            return new ResponseEntity("No game found with id: " + editedGame.getId(), HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") int gameId) {
        this.gameRepository.deleteById(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
