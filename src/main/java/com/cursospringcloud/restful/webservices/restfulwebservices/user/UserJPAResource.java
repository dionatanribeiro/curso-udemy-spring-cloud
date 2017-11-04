package com.cursospringcloud.restful.webservices.restfulwebservices.user;

import com.cursospringcloud.restful.webservices.restfulwebservices.exception.PostNotFoundException;
import com.cursospringcloud.restful.webservices.restfulwebservices.exception.UserNotFoundException;
import com.cursospringcloud.restful.webservices.restfulwebservices.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    @Autowired
    private UserDaoService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        Resource<User> resource = new Resource<>(user.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPostsForUser(@PathVariable int id) {
        List<Post> posts = service.findAllPostsByUser(id);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("user id-" + id);
        }

        return posts;
    }

    @GetMapping("/jpa/users/{id}/posts/{post_id}")
    public Post retrievePostByUser(@PathVariable int id, @PathVariable("post_id") int postId) {
        Post post = service.findOnePostByUser(id, postId);

        if (post == null) {
            throw new PostNotFoundException("user id-" + id + " post id-" + postId);
        }

        return post;
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @RequestBody Post post) {
        Post postSaved = service.savePost(id, post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{post_id}")
                .buildAndExpand(postSaved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }




}
