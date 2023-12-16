package co.com.ibero.inventoried.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;


@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Exception handler for AuthorizationException.
     * <p>
     * Handles the exception and returns an appropriate response entity.
     *
     * @param ex       the AuthorizationException instance
     * @param exchange the ServerWebExchange object
     * @return the ResponseEntity containing the error response
     */
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<AppErrorResponse> handleFounProductReviewException(GenericException ex, ServerWebExchange exchange) {
        String path = exchange.getRequest().getPath().toString();
        AppErrorResponse errorResponse = AppErrorResponse.builder()
                .description(ex.getMessage())
                .statusCode(ex.getStatus().value())
                .path(path)
                .title(ex.getTitle())
                .code(ex.getCode())
                .build();

        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }

}
