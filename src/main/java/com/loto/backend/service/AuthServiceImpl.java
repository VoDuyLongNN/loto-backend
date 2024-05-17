package com.loto.backend.service;

import com.loto.backend.entity.User;
import com.loto.backend.repository.IUserRepository;
import com.loto.backend.request.RegisterRequest;
import com.loto.backend.response.ResponseObject;
import com.loto.backend.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService{
    @Autowired
    private IUserRepository userRepository;


    @Override
    public ResponseObject register(RegisterRequest request) {
        var foundUser = userRepository.findByEmail(request.getEmail());

        if(!foundUser.isPresent()) {
            var user = User.builder()
                    .email(request.getEmail())
                    .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                    .name(request.getName())
                    .gender(request.getGender())
                    .phone(request.getPhone())
                    .role("USER")
                    .build();

            var newUser = userRepository.save(user);

            request.setId(newUser.getId());

            return new ResponseObject(HttpStatus.OK.name(), "Register User Successfully", null);
        }

        return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "User already exist", null);
    }

}
