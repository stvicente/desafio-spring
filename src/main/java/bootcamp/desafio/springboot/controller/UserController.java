package bootcamp.desafio.springboot.controller;

import bootcamp.desafio.springboot.domain.User;
import bootcamp.desafio.springboot.dto.FollowedListDTO;
import bootcamp.desafio.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping(path = "/client/create")
    public ResponseEntity saveClient(@RequestBody User client) {
        return new ResponseEntity(userService.saveClient(client), HttpStatus.CREATED);
    }

    @PostMapping(path = "/seller/create")
    public ResponseEntity saveSeller(@RequestBody User seller) {
        return new ResponseEntity(userService.saveSeller(seller), HttpStatus.CREATED);
    }

    @PostMapping(path = "/{userId}/follow/{userIdToFollow}")
    public Object follow(@PathVariable long userId, @PathVariable long userIdToFollow) {
        return userService.follow(userId, userIdToFollow);
    }

    @PostMapping(path = "/{userId}/unfollow/{userIdToUnfollow}")
    public Object unfollow(@PathVariable long userId, @PathVariable long userIdToUnfollow) {
        return userService.unfollow(userId, userIdToUnfollow);
    }

    @GetMapping(path = "/{userId}/followers/count")
    public ResponseEntity<Object> countFollowers(@PathVariable long userId) {
        return ResponseEntity.ok(userService.countFollowers(userId));
    }

    @GetMapping(path = "/{userId}/followers/list")
    public ResponseEntity<Object> listFollowers(@PathVariable long userId, @RequestParam(required=false) String order) {
        return ResponseEntity.ok(userService.listFollowers(userId, order));
    }

    @GetMapping(path = "/{userId}/followed/list")
    public ResponseEntity<FollowedListDTO> listFollowed(@PathVariable long userId, @RequestParam(required=false) String order) {
        return ResponseEntity.ok(userService.listFollowed(userId, order));
    }
}