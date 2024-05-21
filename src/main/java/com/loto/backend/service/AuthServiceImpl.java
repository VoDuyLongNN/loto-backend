package com.loto.backend.service;

import com.loto.backend.entity.User;
import com.loto.backend.repository.IUserRepository;
import com.loto.backend.request.LoginRequest;
import com.loto.backend.request.RegisterRequest;
import com.loto.backend.response.LoginResponse;
import com.loto.backend.response.ResponseObject;
import com.loto.backend.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements IAuthService{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseObject register(RegisterRequest request) {
        var foundUser = userRepository.findByEmail(request.getEmail());

        if(!foundUser.isPresent()) {
            var user = User.builder()
                    .email(request.getEmail())
                    .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                    .role("USER")
                    .build();

            var newUser = userRepository.save(user);

            request.setId(newUser.getId());

            return new ResponseObject(HttpStatus.OK.name(), "Register User Successfully", null);
        }

        return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "User already exist", null);
    }

    public LoginResponse login(LoginRequest request){
        LoginResponse response = new LoginResponse();

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);

            Date expire = jwtService.getExpirationTimeFromToken(jwtToken, jwtService.getSECRET_KEY());

            response.setUserID(user.getId());
            response.setToken(jwtToken);
            response.setStatus("200");
            response.setExpirartionTime(expire);
            response.setMessage("Login Successfully!");
        } catch (Exception e){
            response.setStatus("500");
            response.setError(e.getMessage());
            response.setMessage("Login Failed!");
        }
        return response;
    }


}
