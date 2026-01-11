package uz.salikhdev.springbootinfinity.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        Long id,
        String role,
        String token
) {
}
