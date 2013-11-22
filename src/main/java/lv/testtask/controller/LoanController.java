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

    @RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> takeLoan(@RequestParam String loanJson, @RequestParam HttpServletRequest request){
        String ip = request.getRemoteAddr();
        //TODO get user from session
        return new ResponseEntity<String>( loanService.takeLoan(loanJson, ip, null), HttpStatus.OK);
    }
    @ExceptionHandler({Exception.class})
    public String handleAll(Exception exception){
        Result result = new Result(Arrays.asList(new ErrorData("global", exception.getMessage())), ResultStatus.ERROR);
        return gson.toJson(result);
    }
}