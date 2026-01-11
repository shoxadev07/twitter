package springbootinfinity.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PostResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("title")
        String title,
        @JsonProperty("description")
        String description,
        @JsonProperty("content_url")
        String contentUrl,
        @JsonProperty("community_name")
        String communityName,
        @JsonProperty("user")
        UserResponse user
) {
}
