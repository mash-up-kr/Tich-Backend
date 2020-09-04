package mashup.backend.tich.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HistoryDoseNotExistException extends BaseException {

    public HistoryDoseNotExistException() {
        this("History does not exist.");
    }

    public HistoryDoseNotExistException(String message) {
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("[ HistoryDoseNotExistException ]\n" + message)
                .build());
    }
}
