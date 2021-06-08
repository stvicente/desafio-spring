package bootcamp.desafio.springboot.service;

import bootcamp.desafio.springboot.domain.User;
import bootcamp.desafio.springboot.dto.*;
import bootcamp.desafio.springboot.exception.BadRequestException;
import bootcamp.desafio.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private User findUserById(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    public Object createUser(UserRequestDTO userRequest) {
        try {
            User user = new User(userRequest.getName(), userRequest.isFollowable());
            return userRepository.save(user);
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> follow(long userId, long userIdToFollow) {
        try {
            User client = findUserById(userId);
            User seller = findUserById(userIdToFollow);
            if(seller.isFollowable() && seller.getId() != client.getId()){
                List<User> follower = seller.getFollower();
                follower.add(client);
                seller.setFollower(follower);
                userRepository.save(seller);
                return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
            }
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>("User is not followable", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> unfollow(long userId, long userIdToUnfollow) {
        try {
            User seller = findUserById(userIdToUnfollow);
            User client = findUserById(userId);
            if (seller.isFollowable() && seller.getId() != client.getId()) {
                List<User> follower = seller.getFollower();
                if(!follower.contains(client)){
                    return new ResponseEntity<>("Seller is not followed", HttpStatus.BAD_REQUEST);
                }
                follower.remove(client);
                seller.setFollower(follower);
                userRepository.save(seller);
                return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
            }
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>("User is not followable", HttpStatus.BAD_REQUEST);
    }

    public Object countFollowers(long userId) {
        try {
            User seller = findUserById(userId);
            if(seller.isFollowable()){
                List<User> following = seller.getFollower();
                CountFollowersDTO count = new CountFollowersDTO();
                count.setFollowersCount(following.size());
                count.setUserId(userId);
                count.setUserName(seller.getName());
                return count;
            }
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>("User is not followable", HttpStatus.BAD_REQUEST);
    }


    private List<BaseDTO> createBaseDTO(List<User> followers){
        List<BaseDTO> baseDTOS = new ArrayList<>();
        for(User follower : followers){
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setUserName(follower.getName());
            baseDTO.setUserId(follower.getId());
            baseDTOS.add(baseDTO);
        }
        return baseDTOS;
    }

    private List<User> listFollowSortedByName(List<User> follow, String order){
        if(order.equals("name_asc")){
            follow.sort((User u1, User u2) -> u1.getName().compareToIgnoreCase(u2.getName()));
        } else if(order.equals("name_desc")){
            follow.sort((User u1, User u2) -> u1.getName().compareToIgnoreCase(u2.getName()));
            Collections.reverse(follow);
            return follow;
        }
        return follow;
    }

    public Object listFollowers(long userId, String order) {
        try{
            User seller = findUserById(userId);
            if(seller.isFollowable()){
                FollowersListDTO followersList = new FollowersListDTO();
                followersList.setUserId(seller.getId());
                followersList.setUserName(seller.getName());
                List<User> followers = order != null ? listFollowSortedByName(seller.getFollower(), order) : seller.getFollower();
                followersList.setFollowers(createBaseDTO(followers));
                return followersList;
            }
        } catch (BadRequestException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.badRequest();
    }

    public Object listFollowed(long userId, String order) {
        try{
            User client = findUserById(userId);
            FollowedListDTO followedList = new FollowedListDTO();
            followedList.setUserId(client.getId());
            followedList.setUserName(client.getName());
            List<User> follows = order != null ? listFollowSortedByName(client.getFollowed(), order) : client.getFollowed();
            followedList.setFollowed(createBaseDTO(follows));
            return followedList;
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
