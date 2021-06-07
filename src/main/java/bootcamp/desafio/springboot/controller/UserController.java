package bootcamp.desafio.springboot.controller;

import bootcamp.desafio.springboot.dto.UserRequestDTO;
import bootcamp.desafio.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/healthcheck")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping(path = "/create")
    public ResponseEntity saveUser(@RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping(path = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> follow(@PathVariable long userId, @PathVariable long userIdToFollow){
        return userService.follow(userId, userIdToFollow);
    }

    @PostMapping(path = "/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollow(@PathVariable long userId, @PathVariable long userIdToUnfollow) {
        return userService.unfollow(userId, userIdToUnfollow);
    }

    @GetMapping(path = "/{userId}/followers/count")
    public Object countFollowers(@PathVariable long userId) {
        return userService.countFollowers(userId);
    }

    @GetMapping(path = "/{userId}/followers/list")
    public ResponseEntity<Object> listFollowers(@PathVariable long userId, @RequestParam(required=false) String order) {
        return ResponseEntity.ok(userService.listFollowers(userId, order));
    }

    @GetMapping(path = "/{userId}/followed/list")
    public ResponseEntity<Object> listFollowed(@PathVariable long userId, @RequestParam(required=false) String order) {
        return ResponseEntity.ok(userService.listFollowed(userId, order));
    }
}