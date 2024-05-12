package kusitms.duduk.common.exception;

import kusitms.duduk.common.exception.custom.AlreadyExistsException;
import kusitms.duduk.common.exception.custom.ConvertException;
import kusitms.duduk.common.exception.custom.NotExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateException(
        final AlreadyExistsException exception) {
        log.error("Duplicated User Information: {}", exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage()));
    }

    @ExceptionHandler(NotExistsException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateException(
        final NotExistsException exception) {
        log.error("Duplicated User Information: {}", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(ConvertException.class)
    public ResponseEntity<ErrorResponse> handleConvertException(
        final ConvertException exception) {
        log.error("Convert Exception: {}", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>(new Exception("Internal Server Error"),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
