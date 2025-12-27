package uz.salikhdev.springbootinfinity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.springbootinfinity.dto.request.PostCreateRequest;
import uz.salikhdev.springbootinfinity.dto.request.PostUpdateRequest;
import uz.salikhdev.springbootinfinity.dto.response.PostResponse;
import uz.salikhdev.springbootinfinity.dto.response.UserResponse;
import uz.salikhdev.springbootinfinity.entity.Community;
import uz.salikhdev.springbootinfinity.entity.Post;
import uz.salikhdev.springbootinfinity.entity.User;
import uz.salikhdev.springbootinfinity.exception.ConflictException;
import uz.salikhdev.springbootinfinity.exception.NotFountException;
import uz.salikhdev.springbootinfinity.repositroy.CommunityRepository;
import uz.salikhdev.springbootinfinity.repositroy.PostRepository;
import uz.salikhdev.springbootinfinity.repositroy.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;

    public void createPost(PostCreateRequest request) {

        if (postRepository.existsByContentUrl(request.contentUrl())) {
            throw new ConflictException("Post already exists");
        }

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new NotFountException("User not found"));

        Post post = Post.builder()
                .title(request.title())
                .description(request.description())
                .contentUrl(request.contentUrl())
                .user(user)
                .build();


        if (request.communityId() != null) {
            Community community = communityRepository
                    .findById(request.communityId())
                    .orElse(null);
            post.setCommunity(community);
        }

        postRepository.save(post);
    }

    public List<PostResponse> getAllPosts() {

        List<Post> all = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponse> responses = new ArrayList<>();

        for (Post post : all) {
            PostResponse postResponse = mapToResponse(post);
            responses.add(postResponse);
        }

        return responses;
    }

    public PostResponse getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Post not found"));

        return mapToResponse(post);
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new NotFountException("Post not found");
        }

        postRepository.deleteById(id);
    }

    public void updatePost(Long id, PostUpdateRequest request) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Post not found"));

        if (request.title() != null && !request.title().isBlank()) {
            post.setTitle(request.title());
        }

        if (request.description() != null && !request.description().isBlank()) {
            post.setDescription(request.description());
        }

        postRepository.save(post);
    }

    private PostResponse mapToResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .contentUrl(post.getContentUrl())
                .user(
                        UserResponse.builder()
                                .id(post.getUser().getId())
                                .fullName(post.getUser().getFirstName() + " " + post.getUser().getLastName())
                                .build()
                )
                .communityName(post.getCommunity() != null ? post.getCommunity().getName() : null)
                .build();
    }
}
