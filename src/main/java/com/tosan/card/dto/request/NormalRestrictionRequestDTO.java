package com.tosan.card.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor

@FieldDefaults(level = PRIVATE)
public class NormalRestrictionRequestDTO extends RestrictionRequestDTO {

}
