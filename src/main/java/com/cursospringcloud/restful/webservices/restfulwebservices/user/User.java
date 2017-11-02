package com.cursospringcloud.restful.webservices.restfulwebservices.user;

import com.cursospringcloud.restful.webservices.restfulwebservices.post.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(description = "Todos os detalhes sobre o User.")
public class User {

    private Integer id;

    @Size(min = 2, message = "Nome deve ter ao menos 2 caracteres")
    @ApiModelProperty(notes = "Nome deve ter ao menos 2 caracteres")
    private String name;

    @Past(message = "Data de nascimento deve ser menor que a data atual")
    @ApiModelProperty(notes = "Data de nascimento deve ser menor que a data atual")
    private Date birthDate;

    private List<Post> posts;

    protected User() { this.posts = new ArrayList<>(); }

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.posts = new ArrayList<>();
    }

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        if (this.posts == null) {
            this.posts = new ArrayList<>();
        }
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
