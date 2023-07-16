package thinhld.ldt.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thinhld.ldt.userservice.model.UserRequest;
import thinhld.ldt.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/test")
    public String testAPI() {
        return "Sucess";
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody UserRequest request) {
        return userService.signIn(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUn(@RequestBody UserRequest request) {
        return userService.signUn(request);
    }

}
