package bootcamp.desafio.springboot.service;

import bootcamp.desafio.springboot.domain.User;
import bootcamp.desafio.springboot.dto.BaseDTO;
import bootcamp.desafio.springboot.dto.CountFollowersDTO;
import bootcamp.desafio.springboot.dto.FollowedListDTO;
import bootcamp.desafio.springboot.dto.FollowersListDTO;
import bootcamp.desafio.springboot.repository.SellerFollowersRepository;
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
//    boolean existsById(ID id);

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
        User seller = findUserById(userIdToFollow);
        User client = findUserById(userId);
        if(seller.isFollowable() && seller.getId() != client.getId()){
            sellerFollowersRepository.follow(client.getId(), seller.getId());
            return "Status Code 200 (tudo OK)";
        }
        return "Status Code 400 (Bad Request)";
//        ResponseStatusException error = new ResponseStatusException(HttpStatus.BAD_REQUEST, "User can not be followed");
//        ResponseEntity ok = new ResponseEntity(HttpStatus.CREATED, HttpStatus.valueOf("User created"));
    }

    public Object unfollow(long userId, long userIdToUnfollow) {
        User seller = findUserById(userIdToUnfollow);
        User client = findUserById(userId);

//        if(seller && client){
            sellerFollowersRepository.unfollow(client.getId(), seller.getId());
            return "Status Code 200 (tudo OK)";
//        }
//        return sellerFollowersRepository.unfollow(userId, userIdToUnfollow);
        //        ResponseStatusException error = new ResponseStatusException(HttpStatus.BAD_REQUEST, "User can not be followed");
//        ResponseEntity ok = new ResponseEntity(HttpStatus.CREATED, HttpStatus.valueOf("User created"));
    }

    public CountFollowersDTO countFollowers(long userId) {
        User seller = findUserById(userId);
        int count = 0;
        if(seller.isFollowable()) count = sellerFollowersRepository.findFollowers(userId).size();
        CountFollowersDTO countFollowersDTO = new CountFollowersDTO(seller.getId(), seller.getName(), count);
        return countFollowersDTO;
    }

    public List<BaseDTO> listFollowerOrFollowed(long userId, String userType){
        List<Long> users = null;
        if(userType=="seller"){
            users = sellerFollowersRepository.findFollowers(userId);
        } else {
            users = sellerFollowersRepository.findFollowed(userId);
        }
        List<BaseDTO> baseDTOS = new ArrayList<>();
        for(long id : users){
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setName(findUserById(id).getName());
            baseDTO.setId(id);
            baseDTOS.add(baseDTO);
        };
        return baseDTOS;
    }

    public FollowersListDTO listFollowers(long userId) {
        User seller = findUserById(userId);
        FollowersListDTO followersList = new FollowersListDTO();
        followersList.setUserId(seller.getId());
        followersList.setUserName(seller.getName());
        List<BaseDTO> followers = listFollowerOrFollowed(seller.getId(), "seller");
        followersList.setFollowers(followers);
        return followersList;
    }

    public FollowedListDTO listFollowed(long userId) {
        User client = findUserById(userId);
        FollowedListDTO followedList = new FollowedListDTO();
        followedList.setUserId(client.getId());
        followedList.setUserName(client.getName());
        List<BaseDTO> followed = listFollowerOrFollowed(client.getId(), "client");
        followedList.setFollowed(followed);
        return followedList;
    }

}
