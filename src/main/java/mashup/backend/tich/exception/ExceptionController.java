package mashup.backend.tich.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<String> restExceptionHandler(BaseException baseException) throws RuntimeException {
        ErrorCode error = baseException.getError();

        return ResponseEntity
                .status(error.getHttpStatus())
                .body(error.getMessage());
    }
}
