package mashup.backend.tich.exception;

        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ItemDoseNotExistException extends BaseException {

    public ItemDoseNotExistException() {
        this("Item does not exist.");
    }

    public ItemDoseNotExistException(Long id) {
        this("Item does not exist with id : " + id + " .");
    }

    public ItemDoseNotExistException(String message) {
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("[ ItemDoseNotExistException ]\n" + message)
                .build());
    }
}
