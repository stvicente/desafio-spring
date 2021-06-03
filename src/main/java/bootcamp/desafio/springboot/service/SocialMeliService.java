package bootcamp.desafio.springboot.service;

import bootcamp.desafio.springboot.domain.SellerFollowers;
import bootcamp.desafio.springboot.domain.User;
//import bootcamp.desafio.springboot.dto.UserPostRequestBody;
import bootcamp.desafio.springboot.dto.CountFollowersDTO;
import bootcamp.desafio.springboot.repository.SellerFollowersRepository;
import bootcamp.desafio.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocialMeliService {
//    private final UserRepository userRepository;
//    private final SellerFollowersRepository sellerFollowersRepository;
//
//    public List<User> listAll(){
//        return userRepository.findAll();
//    }
//
//    public User findUserById(long id){
//        return userRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
//    }
//
//    public User saveClient(User userRequest) {
//        User user = User.builder().name(userRequest.getName()).build();
//        user.setFollowable(false);
//        return userRepository.save(user);
//    }
//
//    public User saveSeller(User userRequest) {
//        User user = User.builder().name(userRequest.getName()).build();
//        user.setFollowable(true);
//        return userRepository.save(user);
//    }
//
//    public Object follow(long userId, long userIdToFollow) {
//        User seller = findUserById(userIdToFollow);
//        ResponseStatusException error = new ResponseStatusException(HttpStatus.BAD_REQUEST, "User can not be followed");
//        ResponseEntity ok = new ResponseEntity(HttpStatus.CREATED, HttpStatus.valueOf("User created"));
//        if(seller.isFollowable()){
//            SellerFollowers sellerFollowers = new SellerFollowers();
//            sellerFollowers.setSeller_id(userIdToFollow);
//            sellerFollowers.setClient_id(userId);
//            sellerFollowersRepository.save(sellerFollowers);
//            return "Status Code 200 (tudo OK)";
//        }
//        return "Status Code 400 (Bad Request)";
//    }
//
//    public CountFollowersDTO countFollowers(long userId) {
//        List<SellerFollowers> sellerFollowers = sellerFollowersRepository.findAll();
//        int count = sellerFollowers.stream()
//                .filter(sf -> sf.getSeller_id() == userId)
//                .collect(Collectors.toList())
//                .size();
//        String userName = findUserById(userId).getName();
//        CountFollowersDTO countFollowersDTO = new CountFollowersDTO();
//        countFollowersDTO.setFollowersCount(count);
//        countFollowersDTO.setUserId(userId);
//        countFollowersDTO.setUserName(userName);
//        return countFollowersDTO;
//    }

//    public FollowersListDTO listFollowers(long userId) {
//        FollowersListDTO followersList = new FollowersListDTO();
//        followersList.setUserId(userId);
//        followersList.setUserName(userRepository.findById(userId).get().getName());
//        List<Long> followers = userRepository.findFollowers(userId);
//        List<BaseDTO> baseDTOS = new ArrayList<>();
//        for(long id : followers){
//            BaseDTO baseDTO = new BaseDTO();
//            baseDTO.setName(userRepository.findById(id).get().getName());
//            baseDTO.setId(id);
//            baseDTOS.add(baseDTO);
//        }
//        followersList.setFollowers(baseDTOS);
//    return followersList;
//    }

}
