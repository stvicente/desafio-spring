package bootcamp.desafio.springboot.controller;

import bootcamp.desafio.springboot.domain.Post;
import bootcamp.desafio.springboot.dto.PostRequestDTO;
import bootcamp.desafio.springboot.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping(path = "/healthcheck")
    public String healthCheck() {
        return "OK";
    }

//    @GetMapping(path = "/followed/{userId}/list")
//    public ResponseEntity<Object> listPosts(@PathVariable long userId) {
//        return new ResponseEntity(postService.listPosts(userId), HttpStatus.ACCEPTED);
//    }

    @GetMapping(path = "/{userId}/countPromo")
    public ResponseEntity<Object> countPromo(@PathVariable long userId){
        return new ResponseEntity(postService.countPromo(userId), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{userId}/promo/list")
    public ResponseEntity<Object> listPosts(@PathVariable long userId){
        return ResponseEntity.ok(postService.listPromoPosts(userId));
    }

    @PostMapping(path = "/newpost")
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDTO post){
        return new ResponseEntity(postService.createPost(post), HttpStatus.CREATED);
    }

    @PostMapping(path = "/newpromopost")
    public ResponseEntity<Post> createPromoPost(@RequestBody PostRequestDTO post){
        return new ResponseEntity(postService.createPromoPost(post), HttpStatus.CREATED);
    }

}

