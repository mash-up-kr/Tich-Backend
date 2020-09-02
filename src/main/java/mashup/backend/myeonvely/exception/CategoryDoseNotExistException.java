package mashup.backend.myeonvely.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CategoryDoseNotExistException extends BaseException {

    public CategoryDoseNotExistException() {
        this("Category does not exist.");
    }

    public CategoryDoseNotExistException(String message) {
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("[ CategoryDoseNotExistException ]\n" + message)
                .build());
    }
}
