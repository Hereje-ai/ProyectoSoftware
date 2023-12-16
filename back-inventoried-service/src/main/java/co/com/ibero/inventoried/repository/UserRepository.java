package co.com.ibero.inventoried.repository;

import co.com.ibero.inventoried.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    @Query(value = " select id from users where document_number=:documentNumber ", nativeQuery = true)
    Long userByDocumentNumber(@Param("documentNumber") String documentNumber);
}
