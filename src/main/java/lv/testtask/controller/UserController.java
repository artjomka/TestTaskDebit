package lv.testtask.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
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
