package lv.testtask.service;

import com.google.gson.Gson;
import lv.testtask.LoanStatus;
import lv.testtask.gson.Result;
import lv.testtask.gson.ResultStatus;
import lv.testtask.persistence.domain.Loan;
import lv.testtask.persistence.domain.LoanExtension;
import lv.testtask.persistence.domain.User;
import lv.testtask.repository.MainRepository;
import lv.testtask.service.util.HazelcastHelper;
import lv.testtask.service.util.ValidationHelper;
import lv.testtask.validation.IpRestrictionData;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            return gson.toJson(loanValidationResult);
        }

        loan.setStatus(LoanStatus.ONGOING);
        user.addLoan(loan);
        mainRepository.saveUser(user);
        restrictionDataForIp.increaseLoanTaken();
        hazelcastHelper.storeIpRestrictionData(ip, restrictionDataForIp);
        return gson.toJson(new Result(ResultStatus.SUCCESS));
    }

    @Override
    public String getLoansForUser(Integer userId) {
        final Set<Loan> loans = mainRepository.getLoansForUser(userId);
        return gson.toJson(loans);
    }

    @Override
    public String extendLoan(String loanData, Integer days, Double amount) {
        final Loan loan = gson.fromJson(loanData, Loan.class);
        return extendLoan(loan,days, amount);
    }

    @Override
    public String extendLoan(Loan loan, Integer days, Double amount) {
        loan.addLoanExtension(new LoanExtension(Money.of(CurrencyUnit.EUR, amount.doubleValue()), days, DateTime.now()));
        loan.setReturnTill(loan.getReturnTill().plusDays(days));
        return gson.toJson(new Result(ResultStatus.SUCCESS));
    }


}
