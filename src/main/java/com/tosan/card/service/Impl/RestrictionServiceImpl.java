package com.tosan.card.service.Impl;


import com.tosan.card.base.service.BaseServiceImpl;
import com.tosan.card.entity.Restriction;
import com.tosan.card.entity.enumuration.Period;
import com.tosan.card.repository.RestrictionRepository;
import com.tosan.card.service.RestrictionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class RestrictionServiceImpl extends BaseServiceImpl<Restriction, Long, RestrictionRepository>
        implements RestrictionService {


    public RestrictionServiceImpl(RestrictionRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Restriction> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void setPeriodDaysFromPeriodType(Restriction restriction) {
        if (!restriction.getPeriod().name().equals(Period.CUSTOM.name())) {
            switch (restriction.getPeriod()) {
                case DAILY -> restriction.setPeriodDays(1);
                case WEEKLY -> restriction.setPeriodDays(7);
                case MONTHLY -> restriction.setPeriodDays(30);
            }
        }
    }

    @Override
    public void setNumberOfDaysLeftRestriction(Restriction restriction) {
        restriction.setNumberOfDaysLeftRestriction(
                restriction.getPeriodDays() *
                restriction.getPeriodRepeat());

    }

    @Override
    public void setPeriodStartDate(Restriction restriction) {
        restriction.setPeriodStartDate(LocalDate.now());
    }
}
