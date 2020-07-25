package mashup.backend.tich.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FaqDoseNotExistException extends BaseException {

    public FaqDoseNotExistException() {
        this("FAQ does not exist.");
    }

    public FaqDoseNotExistException(String message) {
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("[ FaqDoseNotExistException ]\n" + message)
                .build());
    }
}
