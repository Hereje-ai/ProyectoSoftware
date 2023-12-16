package co.com.ibero.inventoried.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.Map;

/**
 * Contains a list of messages indicating invalid arguments.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProblemDetail {
    /**

     This class represents a list of error messages related to invalid arguments in a request.

     It is serialized to JSON and includes only non-null values.
     /
     @JsonInclude(JsonInclude.Include.NON_NULL)
     public class ProblemDetail {
     /*

     A list of error messages related to invalid arguments in a request.
     */
    private Map<String,String> validationErrors;
    /**

     Constructs a new ProblemDetail object with the given validationErrors list.
     @param validationErrors a List of error messages related to invalid arguments in a request.
     */
    public ProblemDetail(Map<String,String> validationErrors) {
        this.validationErrors = validationErrors;
    }
    /**

     Returns the list of error messages related to invalid arguments in a request.
     @return the List of error messages.
     */
    public Map<String,String> getValidationErrors() {
        return validationErrors;
    }
    /**

     Sets the list of error messages related to invalid arguments in a request.
     @param validationErrors the List of error messages to set.
     */
    public void setValidationErrors(Map<String,String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
