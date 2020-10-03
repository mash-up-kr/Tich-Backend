package mashup.backend.tich.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    protected ErrorCode error;

    protected BaseException(ErrorCode error) {
        this.error = error;
    }
}
