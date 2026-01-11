package uz.salikhdev.springbootinfinity.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("content")
        String content,
        @JsonProperty("user")
        UserResponse user,
        @JsonProperty("created_at")
        LocalDateTime createdAt
) {
}
