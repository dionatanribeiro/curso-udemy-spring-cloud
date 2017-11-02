package com.cursospringcloud.restful.webservices.restfulwebservices.post;

import java.util.Date;

public class Post {

    private Integer id;

    private String description;

    private Date dateCreated;

    protected Post() { this.dateCreated = new Date(); }

    public Post(String description) {
        this.description = description;
        this.dateCreated = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

}
