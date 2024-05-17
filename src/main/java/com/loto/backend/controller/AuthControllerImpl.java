package com.loto.backend.controller;

import com.loto.backend.controller.interfaces.IAuthController;
import com.loto.backend.request.RegisterRequest;
import com.loto.backend.response.ResponseObject;
import com.loto.backend.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthControllerImpl implements IAuthController {

    @Autowired
    private IAuthService authService;

    @Override
    @PostMapping("register")
    public ResponseObject register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
}
