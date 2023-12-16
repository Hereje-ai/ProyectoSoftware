package co.com.ibero.inventoried.controller;

import co.com.ibero.inventoried.business.ProductService;
import co.com.ibero.inventoried.business.TransactionService;
import co.com.ibero.inventoried.dto.ProductDTO;
import co.com.ibero.inventoried.dto.ResponseSuccessDTO;
import co.com.ibero.inventoried.dto.TransactionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.request.mappings}/v1/transaction")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    private final TransactionService transactionService;
    @PostMapping(value = "/save")
    public ResponseSuccessDTO saveTransaction(@RequestBody TransactionDTO transactionDTO){

        return transactionService.saveTransaction(transactionDTO);
    }

    @PutMapping(value = "/update")
    public ResponseSuccessDTO updateTransaction(@RequestBody TransactionDTO transactionDTO){

        return transactionService.updateTransaction(transactionDTO);
    }

    @GetMapping(value = "/getTransactiontAll")
    public List<TransactionDTO> getAllTransaction(){
        return transactionService.getAllTransaction();
    }
}
