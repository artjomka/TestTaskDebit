package lv.testtask.controller;

import lv.testtask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(String userJson) {
        try {
            final String result = userService.registerUser(userJson);
            return new ResponseEntity<String>(result, HttpStatus.OK);

        }   catch (Exception exception) {
            LOG.error("Exception during proccessing {}", userJson, exception);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
