package springbootinfinity.dto.request;

import lombok.Builder;

@Builder
public record VerifyRequest(
        String phoneNumber,
        String otp
) {
}
