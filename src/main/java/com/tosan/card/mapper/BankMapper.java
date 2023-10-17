package com.tosan.card.mapper;


import com.tosan.card.dto.request.BankAccountRequestDTO;
import com.tosan.card.dto.response.BankAccountResponseDTO;
import com.tosan.card.entity.BankAccount;
import com.tosan.card.entity.InterestFreeAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankMapper {

    InterestFreeAccount fromBankAccountRequestDTOToInterestFreeAccount(
            BankAccountRequestDTO bankAccountRequestDTO);

    BankAccountResponseDTO fromBankAccountToBankAccountResponseDTO(BankAccount bankAccount);
}
