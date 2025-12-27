package uz.salikhdev.springbootinfinity.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CommentCreateRequest(
        @JsonProperty("user_id")
        @NotBlank(message = "User id is required")
        @NotNull(message = "User id is required")
        @Min(value = 1, message = "User id must be greater than 0")
        Long userId,
        @JsonProperty("post_id")
        Long postId,
        @NotBlank(message = "Content is required")
        @NotNull(message = "Content is required")
        @JsonProperty("content")
        String content
) {
}
