package uz.salikhdev.springbootinfinity.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PostUpdateRequest(
        @JsonProperty("title")
        String title,
        @JsonProperty("description")
        String description
) {
}
