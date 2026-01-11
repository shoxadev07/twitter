package springbootinfinity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springbootinfinity.dto.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflictException(Exception e) {
        return ErrorResponse.of(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFountException.class)
    public ResponseEntity<?> handleNotFound(Exception e) {
        return ErrorResponse.of(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(Exception e) {
        return ErrorResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Exception e) {
        e.printStackTrace();
        return ErrorResponse.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
