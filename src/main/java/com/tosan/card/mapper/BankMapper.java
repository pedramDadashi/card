package com.tosan.card.mapper;


import com.tosan.card.dto.response.BankAccountResponseDTO;
import com.tosan.card.entity.BankAccount;
import com.tosan.card.entity.BankInformation;
import com.tosan.card.entity.InterestFreeAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankMapper {


//    BankInformationRequestDTO convertToBankInformationDTO(
//            BankAccountRequestDTO bankAccountDTO, String nationalCode) {
//        return new BankInformationRequestDTO(
//                bankAccountDTO.getBankName(),
//                bankAccountDTO.getAccountNumber(),
//                nationalCode
//        );
//    }

    InterestFreeAccount convertToInterestFreeAccount(BankInformation bankInformation);

    @Mapping(source = "ba.bankInformation", target = ".")
    BankAccountResponseDTO convertToBankAccountDTO(BankAccount ba);
}
