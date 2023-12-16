package co.com.ibero.inventoried.business;

import co.com.ibero.inventoried.dto.ProductDTO;
import co.com.ibero.inventoried.dto.ResponseSuccessDTO;
import co.com.ibero.inventoried.dto.TransactionDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface TransactionService {

    ResponseSuccessDTO saveTransaction(TransactionDTO transactionDTO);

    ResponseSuccessDTO updateTransaction(TransactionDTO transactionDTO);

    List<TransactionDTO> getAllTransaction();
}
