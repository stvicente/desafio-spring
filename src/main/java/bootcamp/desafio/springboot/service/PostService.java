package bootcamp.desafio.springboot.service;

import bootcamp.desafio.springboot.domain.Post;
import bootcamp.desafio.springboot.domain.Product;
import bootcamp.desafio.springboot.domain.User;
import bootcamp.desafio.springboot.dto.CountPromoDTO;
import bootcamp.desafio.springboot.dto.PostListDTO;
import bootcamp.desafio.springboot.dto.PostRequestDTO;
import bootcamp.desafio.springboot.repository.PostRepository;
import bootcamp.desafio.springboot.repository.ProductRepository;
import bootcamp.desafio.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Post createPost(PostRequestDTO postRequest) {
        User seller = userRepository.findById(postRequest.getUserId()).get();
        Post post = new Post();
        post.setUser(seller);
        post.setDate(postRequest.getDate());
        post.setCategory(postRequest.getCategory());
        post.setPrice(postRequest.getPrice());
        Post postSave = postRepository.save(post);
        List<Product> products = saveProduct(postRequest.getDetails(), postSave);
        postSave.setDetails(products);
        return postRepository.save(postSave);
    }

    public List<Product> saveProduct(List<Product> productsRequest, Post postSave){
        List<Product> products = new ArrayList<>();
        for(Product product : productsRequest){
            Product productSave = new Product();
            productSave.setPost(postSave);
            productSave.setProductName(product.getProductName());
            productSave.setType(product.getType());
            productSave.setBrand(product.getBrand());
            productSave.setColor(product.getColor());
            productSave.setNotes(product.getNotes());
            productRepository.save(productSave);
            products.add(productSave);
        }
        return products;
    }

    public List<Post> listPostsSortedByDate(List<Post> posts, String order){
        if(order.equals("date_asc")){
            posts.sort(Comparator.comparing(Post::getDate));
        } else if(order.equals("date_desc")){
            posts.sort(Comparator.comparing(Post::getDate));
            Collections.reverse(posts);
            return posts;
        }
        return posts;
    }

    public PostListDTO listPosts(long userId, String order) {
        User client = userRepository.getById(userId);
        List<User> follows = client.getFollowed();
        PostListDTO postList = new PostListDTO();
        postList.setUserId(client.getId());
        postList.setUserName(client.getName());
        List<Post> postListPosts = new ArrayList<>();
        for(User followed : follows){
            for(Post post : followed.getPosts())
                postListPosts.add(post);
        }
        postList.setPosts(order != null ? listPostsSortedByDate(postListPosts, order) : postListPosts);
        return postList;

    }
    
    public Post createPromoPost(PostRequestDTO postRequest) {
        Post promoPost = createPost(postRequest);
        promoPost.setHasPromo(true);
        return postRepository.save(promoPost);
    }

    public Object countPromo(long userId) {
        User seller = userRepository.getById(userId);
        if(seller.isFollowable()){
            List<Post> posts = seller.getPosts();
            long count = posts.stream().filter(post -> post.isHasPromo()).count();
            CountPromoDTO countPromo = new CountPromoDTO(seller.getId(), seller.getName(), count);
            return countPromo;
        }
        return "Status Code 400 (Bad Request)";
    }

    public Object listPromoPosts(long userId) {
        User seller = userRepository.getById(userId);
        if(seller.isFollowable()){
            List<Post> posts = seller.getPosts().stream().filter(post -> post.isHasPromo()).collect(Collectors.toList());
            PostListDTO promoPostList = new PostListDTO(seller.getId(), seller.getName(), posts);
            return promoPostList;
        }
        return "Status Code 400 (Bad Request)";
    }
}
