package kusitms.duduk.core.exception;

import kusitms.duduk.core.exception.custom.AlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateException(
        final AlreadyExistsException exception) {
        logger.error("message", exception);

        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage()));
    }
}
