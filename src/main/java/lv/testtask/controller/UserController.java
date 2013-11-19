package lv.testtask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(String userJson) {
          return null;
    }
}
