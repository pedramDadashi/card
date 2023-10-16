package com.tosan.card.mapper;


import com.tosan.card.dto.request.RestrictionRequestDTO;
import com.tosan.card.dto.response.RestrictionResponseDTO;
import com.tosan.card.entity.NormalRestriction;
import com.tosan.card.entity.Restriction;
import org.mapstruct.Mapper;


import java.time.Duration;
import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface RestrictionMapper {

    RestrictionResponseDTO fromRestrictionToRestrictionDTO(Restriction restriction);

//
//    public NormalRestriction convertToNormalRestriction(
//            RestrictionRequestDTO dto) {
//        if (dto.getValidityDaysOfTheRestriction() == 0) {
//            dto.setAmountRestriction(
//                    Duration.between(
//                            LocalDate.now(), dto.getValidityDateOfTheRestriction()
//                    ).toDaysPart()
//            );
//        } else {
//            dto.setValidityDateOfTheRestriction(
//                    LocalDate.now().plusDays(
//                            dto.getValidityDaysOfTheRestriction()
//                    )
//            );
//        }
//        return new NormalRestriction(
//                dto.getName(),
//                dto.getAmountRestriction(),
//                dto.getUseType(),
//                dto.getValidityDateOfTheRestriction(),
//                dto.getValidityDaysOfTheRestriction()
//        );
//    }
//
//    public RestrictionResponseDTO convertToDto(Restriction r) {
//        String period = "";
//        if (!r.getClass().equals(PeriodicRestriction.class))
//            period = "no period";
//        return new RestrictionResponseDTO(
//                r.getName(),
//                r.getAmountRestriction(),
//                r.getUseType().name(),
//                r.getValidityDateOfTheRestriction(),
//                r.getValidityDaysOfTheRestriction(),
//                r.getClient().getId(),
//                period
//        );
//    }
}
