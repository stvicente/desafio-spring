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

    @PostMapping(path = "/newpost")
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDTO post){
        return new ResponseEntity(postService.createPost(post), HttpStatus.CREATED);
    }

    @PostMapping(path = "/newpromopost")
    public ResponseEntity<Post> createPromoPost(@RequestBody PostRequestDTO post){
        return new ResponseEntity(postService.createPromoPost(post), HttpStatus.CREATED);
    }
//
//    @GetMapping(path = "/products/{userId}/countPromo/")
//    public ResponseEntity<Post> listPosts(@PathVariable long userId){
//        return ResponseEntity.ok(socialMeliService.countPromo(userId));
//    }
//
//    @GetMapping(path = "/products/{userId}/promo/list/")
//    public ResponseEntity<Post> listPosts(@PathVariable long userId){
//        return ResponseEntity.ok(socialMeliService.listPromos(userId));
//    }
}

