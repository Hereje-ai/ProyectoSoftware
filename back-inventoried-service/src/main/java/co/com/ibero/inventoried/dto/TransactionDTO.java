package co.com.ibero.inventoried.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {
    private Long id;

    private Date transactionAt;

    private Long transactionTypeId;

    private Long amount;

    private Long productId;

    private String documentNumber;

    private String description;
}
