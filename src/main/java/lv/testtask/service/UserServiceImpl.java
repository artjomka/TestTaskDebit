package lv.testtask.service;

import com.google.gson.Gson;
import lv.testtask.gson.Result;
import lv.testtask.gson.ResultStatus;
import lv.testtask.persistence.domain.Authorities;
import lv.testtask.persistence.domain.User;
import lv.testtask.repository.MainRepository;
import lv.testtask.service.util.ValidationHelper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private Gson gson;
    @Qualifier("mainRepositoryImpl")
    @Autowired
    private MainRepository mainRepository;

    @Qualifier("validationHelper")
    @Autowired
    private ValidationHelper validationHelper;


    @Override
    public String registerUser(String jsonUser) {
        final User user = gson.fromJson(jsonUser, User.class);
        user.setDateRegistered(DateTime.now());
        final Result userValidationResult = validationHelper.getValidationResult(user);
        if (userValidationResult.hasErrors()) {
            return gson.toJson(userValidationResult);

        }

        mainRepository.saveUser(user);
        mainRepository.saveAuthorities(new Authorities(user.getUsername(),"USER"));
        return gson.toJson(new Result(ResultStatus.SUCCESS, Collections.EMPTY_LIST));
    }
}
