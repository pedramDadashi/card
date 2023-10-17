package com.tosan.card.mapper;


import com.tosan.card.dto.request.BankAccountRequestDTO;
import com.tosan.card.entity.InterestFreeAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankMapper {

    InterestFreeAccount fromBankAccountRequestDTOInterestFreeAccount(
            BankAccountRequestDTO bankAccountRequestDTO);

}
