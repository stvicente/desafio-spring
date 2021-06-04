package bootcamp.desafio.springboot.service;

import bootcamp.desafio.springboot.domain.Post;
import bootcamp.desafio.springboot.domain.Product;
import bootcamp.desafio.springboot.domain.User;
import bootcamp.desafio.springboot.dto.PostRequestDTO;
import bootcamp.desafio.springboot.repository.PostRepository;
import bootcamp.desafio.springboot.repository.ProductRepository;
import bootcamp.desafio.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        postSave.setProducts(products);
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
//
//    public Object listPosts(long userId) {
//    }
////
//    public Object unfollow(long userId, long userIdToUnfollow) {
//    }
//
    public Post createPromoPost(PostRequestDTO postRequest) {
        Post promoPost = createPost(postRequest);
        promoPost.setHasPromo(true);
        return postRepository.save(promoPost);
    }
//
//    public Object countPromo(long userId) {
//    }
//
//    public Object listPromos(long userId) {
//    }
}
