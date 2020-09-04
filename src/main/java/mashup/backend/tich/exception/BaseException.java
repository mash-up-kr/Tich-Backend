package mashup.backend.tich.exception;

public class BaseException extends RuntimeException {

    protected ErrorCode error;

    protected BaseException(ErrorCode error) {
        this.error = error;
    }
}
