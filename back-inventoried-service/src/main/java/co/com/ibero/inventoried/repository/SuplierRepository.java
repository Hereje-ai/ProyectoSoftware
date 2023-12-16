package co.com.ibero.inventoried.repository;

import co.com.ibero.inventoried.model.Suplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuplierRepository extends CrudRepository<Suplier,Long> {
}
