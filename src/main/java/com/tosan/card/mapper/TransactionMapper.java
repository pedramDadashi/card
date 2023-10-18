package com.tosan.card.mapper;

import com.tosan.card.dto.TemporaryBankCard;
import com.tosan.card.dto.request.TransactionRequestDTO;
import com.tosan.card.entity.CreditCard;
import com.tosan.card.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction fromTransactionRequestDTOToTemporaryTransaction(TransactionRequestDTO transactionRequestDTO);
}
