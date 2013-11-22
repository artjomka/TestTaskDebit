package lv.testtask.service;

import com.google.gson.Gson;
import lv.testtask.LoanStatus;
import lv.testtask.gson.Result;
import lv.testtask.gson.ResultStatus;
import lv.testtask.persistence.domain.Loan;
import lv.testtask.persistence.domain.User;
import lv.testtask.repository.MainRepository;
import lv.testtask.service.util.HazelcastHelper;
import lv.testtask.service.util.ValidationHelper;
import lv.testtask.validation.IpRestrictionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private Gson gson;

    @Qualifier("mainRepositoryImpl")
    @Autowired
    private MainRepository mainRepository;

    @Autowired
    private HazelcastHelper hazelcastHelper;

    @Autowired
    private ValidationHelper validationHelper;

    @Override
    public String takeLoan(String loanData, String ip, User user) {
        final IpRestrictionData restrictionDataForIp = validationHelper.getUpToDateIpRestriction(hazelcastHelper.getIpRestrictionDataForIp(ip));
        final Result restrictionValidationResult = validationHelper.getValidationResult(restrictionDataForIp);
        if (restrictionValidationResult.hasErrors()) {
            return gson.toJson(restrictionValidationResult);
        }
        final Loan loan = gson.fromJson(loanData, Loan.class);
        final Result loanValidationResult = validationHelper.getValidationResult(loan);
        if (loanValidationResult.hasErrors()) {
            return  gson.toJson(loanValidationResult);
        }

        loan.setStatus(LoanStatus.ONGOING.getValue());
        user.addLoan(loan);
        mainRepository.saveUser(user);
        restrictionDataForIp.increaseLoanTaken();
        hazelcastHelper.storeIpRestrictionData(ip, restrictionDataForIp);
        return gson.toJson(new Result(Collections.EMPTY_LIST, ResultStatus.SUCCESS));
    }

    @Override
    public String getLoansForUser(Integer userId) {
        final Set<Loan> loans = mainRepository.getLoansForUser(userId);
        return gson.toJson(loans);
    }


}
