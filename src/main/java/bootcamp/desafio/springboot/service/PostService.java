package bootcamp.desafio.springboot.service;

import bootcamp.desafio.springboot.domain.Post;
import bootcamp.desafio.springboot.domain.Product;
import bootcamp.desafio.springboot.domain.User;
import bootcamp.desafio.springboot.dto.CountPromoDTO;
import bootcamp.desafio.springboot.dto.PostListDTO;
import bootcamp.desafio.springboot.dto.PostRequestDTO;
import bootcamp.desafio.springboot.exception.BadRequestException;
import bootcamp.desafio.springboot.repository.PostRepository;
import bootcamp.desafio.springboot.repository.ProductRepository;
import bootcamp.desafio.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private User findUserById(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    public ResponseEntity<?> createPost(PostRequestDTO postRequest, boolean hasPromo){
        try {
            User seller = findUserById(postRequest.getUserId());
            Post post = new Post();
            if(seller.isFollowable()) {
                if (!hasPromo) {
                    post = new Post(postRequest.getDate(), postRequest.getCategory(),
                            postRequest.getPrice(), seller);
                } else {
                    post = new Post(postRequest.getDate(), postRequest.getCategory(),
                            postRequest.getPrice(), true, postRequest.getDiscount(), seller);
                }
            } else {
                return new ResponseEntity<>("User is not followable", HttpStatus.BAD_REQUEST);
            }
            Post postSave = postRepository.save(post);
            List<Product> products = saveProduct(postRequest.getDetails(), postSave);
            postSave.setDetails(products);
            postRepository.save(postSave);
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    private List<Product> saveProduct(List<Product> productsRequest, Post postSave){
            List<Product> products = new ArrayList<>();
            for(Product product : productsRequest){
                Product productSave = new Product(product.getProductName(),
                        product.getType(), product.getBrand(), product.getColor(), product.getNotes());
                productSave.setPost(postSave);
                productRepository.save(productSave);
                products.add(productSave);
            }
            return products;
    }

    private List<Post> postDateFilter(List<Post> postList){
        Calendar twoWeeksAgo = Calendar.getInstance();
        twoWeeksAgo.add(Calendar.DAY_OF_MONTH, -14);
        Date twoWeeksAgoDate = twoWeeksAgo.getTime();
        return postList.stream().filter(p -> p.getDate().after(twoWeeksAgoDate)).collect(Collectors.toList());
    }

    private List<Post> listPostsSortedByDate(List<Post> postList, String order){
        List<Post> posts = postDateFilter(postList);
        if(order.equals("date_asc")){
            posts.sort(Comparator.comparing(Post::getDate));
        } else if(order.equals("date_desc")){
            posts.sort(Comparator.comparing(Post::getDate));
            Collections.reverse(posts);
            return posts;
        }
        return posts;
    }

    public Object listPosts(long userId, String order) {
        try{
            User client = findUserById(userId);
            List<User> follows = client.getFollowed();
            PostListDTO postList = new PostListDTO();
            postList.setUserId(client.getId());
            postList.setUserName(client.getName());
            List<Post> postListPosts = new ArrayList<>();
            for(User followed : follows){
                postListPosts.addAll(followed.getPosts());
            }
            String orderPost = order != null ? order : "date_desc";
            postList.setPosts(listPostsSortedByDate(postListPosts, orderPost));
            return postList;
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public Object countPromo(long userId) {
        try{
            User seller = findUserById(userId);
            if(seller.isFollowable()){
                List<Post> posts = seller.getPosts();
                long count = posts.stream().filter(Post::isHasPromo).count();
                return new CountPromoDTO(seller.getId(), seller.getName(), count);
            } else {
                return new ResponseEntity<>("User is not followable", HttpStatus.BAD_REQUEST);
            }
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public Object listPromoPosts(long userId) {
        try {
            User seller = findUserById(userId);
            List<Post> posts;
            if(seller.isFollowable()){
               posts = seller.getPosts().stream().filter(Post::isHasPromo).collect(Collectors.toList());
               return new PostListDTO(seller.getId(), seller.getName(), posts);
            } else {
                return new ResponseEntity<>("User is not followable", HttpStatus.BAD_REQUEST);
            }
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
