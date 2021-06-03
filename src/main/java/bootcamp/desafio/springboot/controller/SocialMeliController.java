package bootcamp.desafio.springboot.controller;

import bootcamp.desafio.springboot.domain.User;
import bootcamp.desafio.springboot.dto.CountFollowersDTO;
import bootcamp.desafio.springboot.dto.FollowListDTO;
import bootcamp.desafio.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("socialmeli")
@RequiredArgsConstructor
public class SocialMeliController {
//    private final SocialMeliService socialMeliService;
    private final UserService userService;
//    private final PostService postService;

    @GetMapping(path = "/healthcheck")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping(path = "/users/client/create")
    public ResponseEntity saveClient(@RequestBody User user){
        return new ResponseEntity(userService.saveClient(user), HttpStatus.CREATED);
    }

    @PostMapping(path = "/users/seller/create")
    public ResponseEntity saveSeller(@RequestBody User seller){
        return new ResponseEntity(userService.saveSeller(seller), HttpStatus.CREATED);
    }

    @PostMapping(path = "/users/{userId}/follow/{userIdToFollow}")
    public Object follow(@PathVariable long userId, @PathVariable long userIdToFollow){
        return userService.follow(userId, userIdToFollow);
    }

    @PostMapping(path = "/users/{userId}/unfollow/{userIdToUnfollow}")
    public Object unfollow(@PathVariable long userId, @PathVariable long userIdToUnfollow){
        return userService.unfollow(userId, userIdToUnfollow);
    }

    @GetMapping(path = "/users/{userId}/followers/count")
    public ResponseEntity<CountFollowersDTO> countFollowers(@PathVariable long userId){
        return ResponseEntity.ok(userService.countFollowers(userId));
    }

    @GetMapping(path = "/users/{userId}/followers/list")
    public ResponseEntity<FollowListDTO> listFollowers(@PathVariable long userId){
        return ResponseEntity.ok(userService.listFollowers(userId));
    }

//    @GetMapping(path = "/users/{userId}/followed/list")
//    public ResponseEntity<FollowListDTO> listFollowed(@PathVariable long userId){
//        return ResponseEntity.ok(userService.listFollowed(userId));
//    }

//
//    @PostMapping(path = "/products/newpost")
//    public ResponseEntity<Post> createPost(@RequestBody Post post){
//        return new ResponseEntity(postService.createPost(post), HttpStatus.CREATED);
//    }
//
//    @PostMapping(path = "/products/newpromopost")
//    public ResponseEntity<Seller> createPost(@RequestBody Post post){
//        return new ResponseEntity(socialMeliService.createPromoPost(post), HttpStatus.CREATED);
//    }
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

