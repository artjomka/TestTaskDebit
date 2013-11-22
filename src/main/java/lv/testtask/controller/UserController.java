package lv.testtask.controller;

import com.google.gson.Gson;
import lv.testtask.gson.ErrorData;
import lv.testtask.gson.Result;
import lv.testtask.gson.ResultStatus;
import lv.testtask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private Gson gson;


    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestParam String userJson) {
        try {
            final String result = userService.registerUser(userJson);
            return new ResponseEntity<String>(result, HttpStatus.OK);

        } catch (Exception exception) {
            LOG.error("Exception during proccessing {}", userJson, exception);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    public String handleAll(Exception exception) {
        Result result = new Result(ResultStatus.ERROR, Arrays.asList(new ErrorData("global", exception.getMessage())));
        return gson.toJson(result);
    }
}
