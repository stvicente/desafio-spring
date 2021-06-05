package bootcamp.desafio.springboot.service;

import bootcamp.desafio.springboot.domain.User;
import bootcamp.desafio.springboot.dto.BaseDTO;
import bootcamp.desafio.springboot.dto.CountFollowersDTO;
import bootcamp.desafio.springboot.dto.FollowedListDTO;
import bootcamp.desafio.springboot.dto.FollowersListDTO;
import bootcamp.desafio.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
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

//    public Object listFollowers(long userId, String order) {
//        System.out.println(order);
//        User seller = findUserById(userId);
//        if(seller.isFollowable() && order == null){
//            return followersList(seller);
//        } else if(seller.isFollowable() && order == "name_asc") {
////            userRepository.findByAndSort(seller.getId(), Sort.by(seller.getPosts().getDate()));
//            FollowersListDTO followers = followersList(seller);
//
//        }
//        return "Status Code 400 (Bad Request)";
//    }

    public List<BaseDTO> createBaseDTO(List<User> followers){
        List<BaseDTO> baseDTOS = new ArrayList<>();
        for(User follower : followers){
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setName(follower.getName());
            baseDTO.setId(follower.getId());
            baseDTOS.add(baseDTO);
        }
        return baseDTOS;
    }

    public List<User> listFollowSortedByName(List<User> follow, String order){
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
        User seller = findUserById(userId);
        if(seller.isFollowable()){
            FollowersListDTO followersList = new FollowersListDTO();
            followersList.setUserId(seller.getId());
            followersList.setUserName(seller.getName());
            List<User> followers = order != null ? listFollowSortedByName(seller.getFollower(), order) : seller.getFollower();
            followersList.setFollowers(createBaseDTO(followers));
            return followersList;
        }
        return "Status Code 400 (Bad Request)";
    }

    public FollowedListDTO listFollowed(long userId, String order) {
        User client = findUserById(userId);
        FollowedListDTO followedList = new FollowedListDTO();
        followedList.setUserId(client.getId());
        followedList.setUserName(client.getName());
        List<User> follows = order != null ? listFollowSortedByName(client.getFollowed(), order) : client.getFollowed();
        followedList.setFollowed(createBaseDTO(follows));
        return followedList;
    }

}
