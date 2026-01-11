package springbootinfinity.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record UserResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("full_name")
        String fullName
) {
}
