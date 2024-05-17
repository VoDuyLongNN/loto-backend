package com.loto.backend.controller.interfaces;

import com.loto.backend.request.LoginRequest;
import com.loto.backend.request.RegisterRequest;
import com.loto.backend.response.LoginResponse;
import com.loto.backend.response.ResponseObject;

public interface IAuthController {
    public ResponseObject register(RegisterRequest registerRequest);
    public LoginResponse login(LoginRequest loginRequest);
}
