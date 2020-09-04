package mashup.backend.tich.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResultDoseNotExistException extends BaseException {

    public ResultDoseNotExistException() {
        this("Result does not exist.");
    }

    public ResultDoseNotExistException(String message) {
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("[ ResultDoseNotExistException ]\n" + message)
                .build());
    }
}
