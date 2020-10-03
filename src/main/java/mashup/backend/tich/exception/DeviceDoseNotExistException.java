package mashup.backend.tich.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DeviceDoseNotExistException extends BaseException {

    public DeviceDoseNotExistException() {
        this("Device does not exist.");
    }

    public DeviceDoseNotExistException(String message) {
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("[ DeviceDoseNotExistException ]\n" + message)
                .build());
    }
}
