package com.tosan.card.mapper;


import com.tosan.card.dto.request.PeriodicRestrictionRequestDTO;
import com.tosan.card.dto.response.RestrictionResponseDTO;
import com.tosan.card.entity.PeriodicRestriction;
import com.tosan.card.entity.Restriction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestrictionMapper {

    RestrictionResponseDTO fromRestrictionToRestrictionDTO(Restriction restriction);

    PeriodicRestriction fromPeriodicRestrictionRequestDTOToPeriodicRestriction(
            PeriodicRestrictionRequestDTO periodicRestrictionRequestDTO);

}
