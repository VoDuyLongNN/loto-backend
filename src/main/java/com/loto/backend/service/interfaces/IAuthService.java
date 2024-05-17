package com.loto.backend.service.interfaces;

import com.loto.backend.request.LoginRequest;
import com.loto.backend.request.RegisterRequest;
import com.loto.backend.response.LoginResponse;
import com.loto.backend.response.ResponseObject;

public interface IAuthService {
    ResponseObject register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
