package springbootinfinity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootinfinity.dto.request.CommentCreateRequest;
import springbootinfinity.dto.response.CommentResponse;
import springbootinfinity.dto.response.UserResponse;
import springbootinfinity.entity.Comment;
import springbootinfinity.entity.Post;
import springbootinfinity.entity.User;
import springbootinfinity.exception.NotFountException;
import springbootinfinity.repositroy.CommentRepository;
import springbootinfinity.repositroy.PostRepository;
import springbootinfinity.repositroy.UserRepository;

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
