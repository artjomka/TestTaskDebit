package lv.testtask.controller;

import lv.testtask.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/loan")
public class LoanController {
    private static final Logger LOG = LoggerFactory.getLogger(LoanController.class);

    @Qualifier("loanServiceImpl")
    @Autowired
    private LoanService loanService;

    @RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> takeLoan(String loanJson, HttpServletRequest request){
        String ip = request.getRemoteAddr();
        return new ResponseEntity<String>( loanService.takeLoan(loanJson, ip), HttpStatus.OK);
    }
}