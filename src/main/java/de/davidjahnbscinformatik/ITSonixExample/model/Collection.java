package de.davidjahnbscinformatik.ITSonixExample.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "collections")
public class Collection {

    @Id
    private int id;

    @Column
    private Date createdOn;

    @Column
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "collection_Id")
    private Set<Game> gamelist;


    public Collection(){

    }

    public Collection(String collectionName){
        this.createdOn = new Date();
        this.name = name;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Game> getGamelist() {
        return gamelist;
    }

    public void setGamelist(Set<Game> gamelist) {
        this.gamelist = gamelist;
    }
}
