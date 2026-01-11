package springbootinfinity.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @JsonProperty("phone_number")
        @Schema(example = "+998909405577")
        String phoneNumber,
        @JsonProperty("first_name")
        @Schema(example = "Salikh")
        String firstName,
        @JsonProperty("last_name")
        @Schema(example = "Salikhov")
        String lastName,
        @JsonProperty("password")
        @Schema(example = "admin123")
        String password,
        @JsonProperty("email")
        @Schema(example = "salikhdev@gmail.com")
        String email,
        @JsonProperty("username")
        @Schema(example = "salikhdev")
        String username
) {
}
