package co.com.ibero.inventoried.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String firstName;

    private String secondName;

    private String lastName;

    private String documentNumber;

    private String email;

    private String telephone;

    private String address;

    private Long userType;

}
