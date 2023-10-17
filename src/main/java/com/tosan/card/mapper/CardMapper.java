package com.tosan.card.mapper;

import com.tosan.card.dto.TemporaryBankCard;
import com.tosan.card.entity.Card;
import com.tosan.card.entity.CreditCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CreditCard fromTemporaryBankCardToCreditCard(TemporaryBankCard temporaryBankCard);

}
