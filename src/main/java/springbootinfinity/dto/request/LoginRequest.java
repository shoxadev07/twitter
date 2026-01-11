package springbootinfinity.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record LoginRequest(
        @JsonProperty("phone_number")
        @Schema(example = "+998909405577")
        String phoneNumber,
        @JsonProperty("password")
        @Schema(example = "admin123")
        String password
) {
}
