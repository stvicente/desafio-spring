package bootcamp.desafio.springboot.service;

//import bootcamp.desafio.springboot.dto.UserPostRequestBody;
import bootcamp.desafio.springboot.domain.Post;
import bootcamp.desafio.springboot.domain.User;
import bootcamp.desafio.springboot.repository.PostRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Object createPost(Post postRequest) {
//        Post postBuilder = new Post();
//        Post post = Post.builder().postBuilder(postRequest).build();
        return postRepository.save(postRequest);
    }
//
//    public Object listPosts(long userId) {
//    }
//
//    public Object unfollow(long userId, long userIdToUnfollow) {
//    }
//
//    public Object createPromoPost(Post post) {
//    }
//
//    public Object countPromo(long userId) {
//    }
//
//    public Object listPromos(long userId) {
//    }
}
