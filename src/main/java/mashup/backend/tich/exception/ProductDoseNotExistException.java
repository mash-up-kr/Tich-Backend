package mashup.backend.tich.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductDoseNotExistException extends BaseException {

    public ProductDoseNotExistException() {
        this("Product does not exist.");
    }

    public ProductDoseNotExistException(String message) {
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("[ ProductDoseNotExistException ]\n" + message)
                .build());
    }
}
