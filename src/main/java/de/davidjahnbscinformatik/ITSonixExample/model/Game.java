package de.davidjahnbscinformatik.ITSonixExample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import javax.validation.constraints.Pattern;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[A-Z]{4}-[0-9]{5}$")
    private String productId;
    private String imgPath;
    @Column(nullable = false)
    private Integer platformId;
    @Column(nullable = false)
    private Integer collection_Id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getCollection_Id() {
        return collection_Id;
    }

    public void setCollection_Id(Integer collection_Id) {
        this.collection_Id = collection_Id;
    }
}
