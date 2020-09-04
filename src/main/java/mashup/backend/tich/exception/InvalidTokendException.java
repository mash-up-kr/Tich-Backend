package mashup.backend.tich.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokendException extends BaseException {

    public InvalidTokendException(String message){
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("[ InvalidTokenException ]\n" + message)
                .build());
    }
}
