package com.example.planmyvacation.validation;

import com.example.planmyvacation.model.dto.PlanCreateDTO;
import com.example.planmyvacation.model.dto.UserRegisterDTO;
import com.example.planmyvacation.repository.CityRepository;
import com.example.planmyvacation.repository.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;

@Component
public final class PlanCreateValidation {

    private final MessageSource messageSource;
    private final CityRepository cityRepository;

    private PlanCreateValidation(MessageSource messageSource, CityRepository cityRepository) {
        this.messageSource = messageSource;
        this.cityRepository = cityRepository;
    }

    public BindingResult startDateBeforeEndDate(PlanCreateDTO planDTO, BindingResult result) {

        LocalDate startDate = planDTO.getStartDate();
        LocalDate endDate = planDTO.getEndDate();

        if ((startDate != null && endDate != null) && !startDate.isBefore(endDate)) {

            FieldError error = getFieldError("msg.start.date.not.before.end.date", "endDate");
            result.addError(error);
        }

        return result;
    }

    public BindingResult cityExists(PlanCreateDTO planDTO, BindingResult result) {

        String cityName = planDTO.getCityName();

        if (cityName != null && !cityRepository.existsCityByName(cityName)) {

            FieldError error = getFieldError("msg.city.not.exists", "cityName");
            result.addError(error);
        }

        return result;
    }

    private FieldError getFieldError(String messageCode, String fieldName) {

        String errorMessageLocalized = messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());

        return new FieldError("planCreateDTO", fieldName, errorMessageLocalized);
    }

}
