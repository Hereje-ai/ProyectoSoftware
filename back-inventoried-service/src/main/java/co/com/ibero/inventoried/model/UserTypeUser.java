package co.com.ibero.inventoried.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_type_user")
public class UserTypeUser {

    @Id
    private Long id;

    private Long usersId;

    private Long typeUserId;
}
