package co.com.ibero.inventoried.repository;

import co.com.ibero.inventoried.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {
}
