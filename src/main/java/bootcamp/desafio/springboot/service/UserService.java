package bootcamp.desafio.springboot.service;

import bootcamp.desafio.springboot.domain.User;
import bootcamp.desafio.springboot.dto.*;
import bootcamp.desafio.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final SellerFollowersRepository sellerFollowersRepository;
    private final UserRepository userRepository;

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User findUserById(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    public User saveClient(User userRequest) {
        User user = User.builder().name(userRequest.getName()).build();
        user.setFollowable(false);
        return userRepository.save(user);
    }

    public User saveSeller(User userRequest) {
        User user = User.builder().name(userRequest.getName()).build();
        user.setFollowable(true);
        return userRepository.save(user);
    }

    public Object follow(long userId, long userIdToFollow) {
        User client = findUserById(userId);
        User seller = findUserById(userIdToFollow);
        if(seller.isFollowable() && seller.getId() != client.getId()){
            List follower = seller.getFollower();
            follower.add(client);
            seller.setFollower(follower);
            userRepository.save(seller);
            return "Status Code 200 (tudo OK)";
        }
        return "Status Code 400 (Bad Request)";
//        ResponseStatusException error = new ResponseStatusException(HttpStatus.BAD_REQUEST, "User can not be followed");
//        ResponseEntity ok = new ResponseEntity(HttpStatus.CREATED, HttpStatus.valueOf("User created"));
    }

    public Object unfollow(long userId, long userIdToUnfollow) {
        User seller = findUserById(userIdToUnfollow);
        User client = findUserById(userId);
        if(seller.isFollowable() && seller.getId() != client.getId()){
            List follower = seller.getFollower();
            follower.remove(client);
            seller.setFollower(follower);
            userRepository.save(seller);
            return "Status Code 200 (tudo OK)";
        }
        return "Status Code 400 (Bad Request)";
    }

    public Object countFollowers(long userId) {
        User seller = findUserById(userId);
        if(seller.isFollowable()){
            List following = seller.getFollower();
            CountFollowersDTO count = new CountFollowersDTO();
            count.setFollowersCount(following.size());
            count.setUserId(userId);
            count.setUserName(seller.getName());
            return count;
        }
        return "Status Code 400 (Bad Request)";
    }

    public Object listFollowers(long userId) {
        User seller = findUserById(userId);
        if(seller.isFollowable()){
            FollowersListDTO followersList = new FollowersListDTO();
            followersList.setUserId(seller.getId());
            followersList.setUserName(seller.getName());
            List<User> followers = seller.getFollower();
            List<BaseDTO> baseDTOS = new ArrayList<>();
            for(User follower : followers){
                BaseDTO baseDTO = new BaseDTO();
                baseDTO.setName(follower.getName());
                baseDTO.setId(follower.getId());
                baseDTOS.add(baseDTO);
            }
            followersList.setFollowers(baseDTOS);
            return followersList;
        }
        return "Status Code 400 (Bad Request)";
    }

    public FollowedListDTO listFollowed(long userId) {
            User client = findUserById(userId);
            FollowedListDTO followedList = new FollowedListDTO();
            followedList.setUserId(client.getId());
            followedList.setUserName(client.getName());
            List<User> follows = client.getFollowed();
            List<BaseDTO> baseDTOS = new ArrayList<>();
            for(User followed : follows){
                BaseDTO baseDTO = new BaseDTO();
                baseDTO.setName(followed.getName());
                baseDTO.setId(followed.getId());
                baseDTOS.add(baseDTO);
            }
            followedList.setFollowed(baseDTOS);
            return followedList;
    }

}
