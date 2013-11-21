package lv.testtask.service.util;

import lv.testtask.gson.ErrorData;
import lv.testtask.gson.Result;
import lv.testtask.gson.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class ValidationHelper {

    @Autowired
    private Validator validator;

    public <T> Result getValidationResult(T object) {
        final Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        if (!constraintViolations.isEmpty()) {
            List<ErrorData> errorMessages = new ArrayList<ErrorData>();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                errorMessages.add(new ErrorData(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()));
            }
            return new Result(errorMessages, ResultStatus.ERROR);
        }
        return new Result(Collections.EMPTY_LIST, ResultStatus.SUCCESS);
    }
}
