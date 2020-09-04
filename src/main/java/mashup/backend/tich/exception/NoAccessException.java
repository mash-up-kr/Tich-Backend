package mashup.backend.tich.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoAccessException extends BaseException {

    public NoAccessException() {
        this("This user does not have access.");
    }

    public NoAccessException(String message) {
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("[ NoAccessException ]\n" + message)
                .build());
    }
}
