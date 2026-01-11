package springbootinfinity.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootinfinity.dto.request.CommentCreateRequest;
import springbootinfinity.dto.response.SuccessResponse;
import springbootinfinity.service.CommentService;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Tag(name = "Comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody CommentCreateRequest request) {
        commentService.createComment(request);
        return ResponseEntity.ok(SuccessResponse.created("Comment created successfully"));
    }

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam("post_id") Long postId) {
        return ResponseEntity.ok(commentService.getComments(postId));
    }

}
