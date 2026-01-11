package uz.salikhdev.springbootinfinity.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
public record ErrorResponse(
        String message,
        int code,
        boolean status
) {
    public static ResponseEntity<?> of(String message, HttpStatus code) {
        return ResponseEntity
                .status(code.value())
                .body(
                        ErrorResponse.builder()
                                .message(message)
                                .code(code.value())
                                .status(false)
                                .build()
                );
    }
}
