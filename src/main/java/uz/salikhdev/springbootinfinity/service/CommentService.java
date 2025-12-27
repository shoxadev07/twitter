package uz.salikhdev.springbootinfinity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.springbootinfinity.dto.request.CommentCreateRequest;
import uz.salikhdev.springbootinfinity.dto.response.CommentResponse;
import uz.salikhdev.springbootinfinity.dto.response.UserResponse;
import uz.salikhdev.springbootinfinity.entity.Comment;
import uz.salikhdev.springbootinfinity.entity.Post;
import uz.salikhdev.springbootinfinity.entity.User;
import uz.salikhdev.springbootinfinity.exception.NotFountException;
import uz.salikhdev.springbootinfinity.repositroy.CommentRepository;
import uz.salikhdev.springbootinfinity.repositroy.PostRepository;
import uz.salikhdev.springbootinfinity.repositroy.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public void createComment(CommentCreateRequest request) {

        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new NotFountException("Post not found"));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new NotFountException("User not found"));


        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(request.content())
                .build();

        commentRepository.save(comment);
    }

    public List<CommentResponse> getComments(Long postId) {

        List<Comment> all = commentRepository.findAllByPostId(postId);
        List<CommentResponse> responses = new ArrayList<>();

        for (Comment comment : all) {
            CommentResponse response = mapToResponse(comment);
            responses.add(response);
        }

        return responses;
    }

    private CommentResponse mapToResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .user(
                        UserResponse.builder()
                                .id(comment.getUser().getId())
                                .fullName(comment.getUser().getFirstName() + " " + comment.getUser().getLastName())
                                .build()
                )
                .build();
    }

}
