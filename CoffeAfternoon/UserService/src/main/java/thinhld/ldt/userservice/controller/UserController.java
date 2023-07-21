package thinhld.ldt.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.userservice.model.User;
import thinhld.ldt.userservice.model.UserRequest;
import thinhld.ldt.userservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/test")
    public String testAPI() {
        return "Sucess";
    }
    @GetMapping("/get-all")
    public List<User> getAll() {
        return userService.getAllUser();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody UserRequest request) {
        return userService.signIn(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUn(@RequestBody UserRequest request) {
        return userService.signUp(request);
    }

}
