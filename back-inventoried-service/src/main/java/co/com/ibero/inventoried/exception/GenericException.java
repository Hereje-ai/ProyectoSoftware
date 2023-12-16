package co.com.ibero.inventoried.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GenericException extends  RuntimeException {
    private String message;
    private String code;
    private String title;
    private HttpStatus status;

    /**
     * Constructs a new NotFoundProductReviewException with the specified detail message, code, and title.
     *
     * @param message the detail message.
     */
    public GenericException(String message, String code, String title, HttpStatus status) {
        this.message = message;
        this.code = code;
        this.title = title;
        this.status = status;
    }

}
