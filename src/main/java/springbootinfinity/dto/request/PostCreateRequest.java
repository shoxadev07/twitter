package springbootinfinity.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PostCreateRequest(
        @JsonProperty("user_id")
        @NotBlank(message = "User id is required")
        @NotNull(message = "User id is required")
        @Min(value = 1, message = "User id must be greater than 0")
        Long userId,
        @JsonProperty("title")
        String title,
        @JsonProperty("description")
        String description,
        @JsonProperty("content_url")
        @NotBlank(message = "Content url is required")
        @NotNull(message = "Content url is required")
        String contentUrl,
        @JsonProperty("community_id")
        @Min(value = 1, message = "Community id must be greater than 0")
        Long communityId
) {
}
