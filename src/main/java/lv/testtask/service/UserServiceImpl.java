package lv.testtask.service;

import com.google.gson.Gson;
import lv.testtask.gson.ErrorData;
import lv.testtask.persistence.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private Gson gson;

    @Autowired
    private Validator validator;


    @Override
    public String registerUser(String jsonUser) {
        final User user = gson.fromJson(jsonUser, User.class);
        final Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        if (!constraintViolations.isEmpty()) {
            List<ErrorData> errorMessages = new ArrayList<ErrorData>();
            for (ConstraintViolation<User> constraintViolation : constraintViolations) {
                errorMessages.add(new ErrorData(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()));
            }
        }
        return null;
    }
}
