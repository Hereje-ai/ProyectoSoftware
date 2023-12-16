package co.com.ibero.inventoried.business.impl;

import co.com.ibero.inventoried.business.TransactionService;
import co.com.ibero.inventoried.dto.ProductDTO;
import co.com.ibero.inventoried.dto.ResponseSuccessDTO;
import co.com.ibero.inventoried.dto.TransactionDTO;
import co.com.ibero.inventoried.exception.GenericException;
import co.com.ibero.inventoried.model.Product;
import co.com.ibero.inventoried.model.Transaction;
import co.com.ibero.inventoried.repository.ProductRepository;
import co.com.ibero.inventoried.repository.TransactionRepository;
import co.com.ibero.inventoried.repository.TypeTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final ProductRepository productRepository;

    private final TypeTransactionRepository typeTransactionRepository;

    @Override
    public ResponseSuccessDTO saveTransaction(TransactionDTO transactionDTO) {
        log.info("Inicio de metodo saveTransaction");
        var transactionSave = Transaction.builder().transactionAt(new Date()).transactionTypeId(transactionDTO.getTransactionTypeId()).productId(transactionDTO.getProductId())
                .amount(transactionDTO.getAmount()).description(transactionDTO.getDescription()).documentNumber(transactionDTO.getDocumentNumber()).build();

        var validate = transactionRepository.save(transactionSave);

        var product = productRepository.findById(transactionSave.getProductId()).get();

        var typeTransaction = typeTransactionRepository.findById(transactionSave.getTransactionTypeId());

        if("salida".equalsIgnoreCase(typeTransaction.get().getName())){
            product.setQuantityAvailable(product.getQuantityAvailable() - transactionSave.getAmount());
        }else{
            product.setQuantityAvailable(product.getQuantityAvailable() + transactionSave.getAmount());
        }
        log.info("cantidad disponible : " + product.getQuantityAvailable());
        var result = productRepository.save(product);
        log.info("resultado de nueva cantidad -> {} " + result);
        if (validate.getId() == null) {
            throw new GenericException("BAD_ERROR", "Error al crear la transaccion", "Lo sentimos.", HttpStatus.BAD_REQUEST);
        }
        return ResponseSuccessDTO.builder().code(1L).message("Transacción creada correctamente").build();
    }

    @Override
    public ResponseSuccessDTO updateTransaction(TransactionDTO transactionDTO) {
        log.info("Inicio de metodo updateTransaction");
        var transactionSave = Transaction.builder().id(transactionDTO.getId()).transactionAt(new Date()).transactionTypeId(transactionDTO.getTransactionTypeId())
                .amount(transactionDTO.getAmount()).description(transactionDTO.getDescription()).documentNumber(transactionDTO.getDocumentNumber()).build();
        var validate = transactionRepository.save(transactionSave);
        if (validate.getId() == null) {
            throw new GenericException("BAD_ERROR", "Error al actualizar la transaccion", "Lo sentimos.", HttpStatus.BAD_REQUEST);
        }
        return ResponseSuccessDTO.builder().code(1L).message("Transacción actualizada correctamente").build();
    }

    @Override
    public List<TransactionDTO> getAllTransaction() {
        log.info("Inicio de metodo getAllTransaction");
        var transaction = transactionRepository.findAll();
        log.info("Product -> {} " + transaction);
        return convertTransactioListToDTOList((List<Transaction>) transaction);
    }

    public List<TransactionDTO> convertTransactioListToDTOList(List<Transaction> transactionList) {
        List<TransactionDTO> list = new ArrayList<>();

        for (Transaction transactionDTO : transactionList) {
            var transaction = TransactionDTO.builder().id(transactionDTO.getId()).transactionAt(new Date()).transactionTypeId(transactionDTO.getTransactionTypeId())
                    .amount(transactionDTO.getAmount()).description(transactionDTO.getDescription()).documentNumber(transactionDTO.getDocumentNumber())
                    .productId(transactionDTO.getProductId()).build();
            list.add(transaction);
        }
        return list;
    }
}
