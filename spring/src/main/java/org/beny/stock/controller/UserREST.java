package org.beny.stock.controller;

import org.beny.stock.dto.user.ResendRequest;
import org.beny.stock.dto.user.UserRequest;
import org.beny.stock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.beny.stock.util.CaptchaUtil.verifyCaptcha;

@RestController
@RequestMapping("/rest")
public class UserREST extends BaseREST {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest userRequest) throws RuntimeException {
        verifyCaptcha(userRequest.getCaptchaResponse());
        service.create(userRequest.getUser());
        return ok();
    }

    @GetMapping("/register/activate")
    public ResponseEntity<?> activate(@RequestParam("token") String token) throws RuntimeException {
        service.activate(token);
        return ok();
    }

    @PostMapping("/register/resend")
    public ResponseEntity<?> resendToken(@Valid @RequestBody ResendRequest resendRequest) throws RuntimeException {
        service.resendToken(resendRequest.getEmail());
        return ok();
    }

}
