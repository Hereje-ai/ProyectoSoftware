package co.com.ibero.inventoried.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Class representing a standard API error response, including an HTTP status code and error details in a ProblemDetail object.
 * <p>
 * The class is annotated with @JsonInclude(JsonInclude.Include.NON_NULL) to exclude fields with null values during serialization.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@AllArgsConstructor
public class AppErrorResponse {
    private final Integer codeStatusHttp;
    private ProblemDetail body;
    private String description;
    private Integer statusCode;
    private String title;
    private String code;
    private String path ;

    public AppErrorResponse(Integer status, String description) {
        this.codeStatusHttp = status;
        this.description = description;
    }

    public AppErrorResponse(Integer status, ProblemDetail body) {
        this.codeStatusHttp = status;
        this.body = body;
    }



}