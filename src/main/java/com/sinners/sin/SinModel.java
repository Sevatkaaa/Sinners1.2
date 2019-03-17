package com.sinners.sin;


import com.sinners.user.UserModel;

import javax.persistence.*;

@Entity
public class SinModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private Integer weight;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel author;

    public SinModel() {
    }

    public SinModel(String type, Integer weight, String description) {
        this.type = type;
        this.weight = weight;
        this.description = description;
    }

    public SinModel(String type, Integer weight, String description, UserModel author) {
        this.type = type;
        this.weight = weight;
        this.description = description;
        this.author = author;
    }

    public String getAuthorName() {
        return author.getUsername();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

}
