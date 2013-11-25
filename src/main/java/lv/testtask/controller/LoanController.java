package lv.testtask.controller;

import com.google.gson.Gson;
import lv.testtask.gson.ErrorData;
import lv.testtask.gson.Result;
import lv.testtask.gson.ResultStatus;
import lv.testtask.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequestMapping("/loan")
public class LoanController {
    private static final Logger LOG = LoggerFactory.getLogger(LoanController.class);
    @Autowired
    private LoanService loanService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> printWelcome(ModelMap model) {

        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @RequestMapping(value = "/takeLoan", method = RequestMethod.POST)
    public ResponseEntity<String> takeLoan(@RequestParam String loanJson) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        //TODO get user from session
        return new ResponseEntity<>(loanService.takeLoan(loanJson, ip, null), HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleAll(Exception exception) {
        Result result = new Result(ResultStatus.ERROR, Arrays.asList(new ErrorData("global", exception.getMessage())));
        return new ResponseEntity<>(gson.toJson(result), HttpStatus.OK);
    }
}