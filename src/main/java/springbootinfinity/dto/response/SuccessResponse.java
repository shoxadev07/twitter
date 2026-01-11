package springbootinfinity.dto.response;

import lombok.Builder;

@Builder
public record SuccessResponse(
        String message,
        int code,
        boolean status
) {
    public static SuccessResponse created(String message) {
        return SuccessResponse.builder()
                .message(message)
                .code(201)
                .status(true)
                .build();
    }

    public static SuccessResponse ok(String message) {
        return SuccessResponse.builder()
                .message(message)
                .code(200)
                .status(true)
                .build();
    }
}
