package uz.salikhdev.springbootinfinity.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.salikhdev.springbootinfinity.dto.request.PostCreateRequest;
import uz.salikhdev.springbootinfinity.dto.request.PostUpdateRequest;
import uz.salikhdev.springbootinfinity.dto.response.SuccessResponse;
import uz.salikhdev.springbootinfinity.service.PostService;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "Post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostCreateRequest request) {
        postService.createPost(request);
        return ResponseEntity.ok(SuccessResponse.created("Post created successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(SuccessResponse.ok("Post deleted successfully"));
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        postService.updatePost(id, request);
        return ResponseEntity.ok(SuccessResponse.ok("Post updated successfully"));
    }

}
