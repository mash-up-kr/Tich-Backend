package mashup.backend.tich.exception;

import org.springframework.http.HttpStatus;

public class CanNotSendMessageException extends BaseException {

    public CanNotSendMessageException() {
        this("실패");
    }

    public CanNotSendMessageException(String message) {
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("[ CanNotSendMessageException ]\n" + message)
                .build());
    }
}
