package com.tosan.card.service;



import com.tosan.card.base.service.BaseService;
import com.tosan.card.entity.Restriction;

import java.util.Optional;

public interface RestrictionService extends BaseService<Restriction,Long> {

    Optional<Restriction> findByName(String name);

    void setPeriodDaysFromPeriodType(Restriction restriction);

    void setNumberOfDaysLeftRestriction(Restriction restriction);

    void setPeriodStartDate(Restriction restriction);
}
