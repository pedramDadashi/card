package com.tosan.card.mapper;


import com.tosan.card.dto.request.RegularClientRegistrationDTO;

import com.tosan.card.entity.RegularClient;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClientMapper {

     RegularClient fromRegularClientRegistrationDTOToNewRegularClient(RegularClientRegistrationDTO dto);

}
